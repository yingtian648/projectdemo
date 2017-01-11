package mine.demo1.okhttp_qcloud.https;

import android.content.Context;

import java.lang.reflect.Method;

public class DefaultUtil {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static Object invoke(final Object obj, final String methodName,
								@SuppressWarnings("rawtypes") final Class[] classes,
								final Object[] objects) {
		try {
			Method method = getMethod(obj.getClass(), methodName, classes);
			method.setAccessible(true);// 调用private方法的关键一句话
			return method.invoke(obj, objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invokeSub(final Object obj, final String methodName,
								   @SuppressWarnings("rawtypes") final Class[] classes,
								   final Object[] objects) {
		try {
			Method method = getMethod(obj.getClass(), methodName, classes);
			method.setAccessible(true);// 调用private方法的关键一句话
			return method.invoke(obj, objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getMethod(Class clazz, String methodName,
								   final Class[] classes) throws Exception {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, classes);
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod(methodName, classes);
			} catch (NoSuchMethodException ex) {
				if (clazz.getSuperclass() == null) {
					return method;
				} else {
					method = getMethod(clazz.getSuperclass(), methodName,
							classes);
				}
			}
		}
		return method;
	}

	
	
	public static int getAndroidOSVersion()
    {
     	 int osVersion;
     	 try
     	 {
     		osVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
     	 }
     	 catch (NumberFormatException e)
     	 {
     		osVersion = 0;
     	 }
     	 
     	 return osVersion;
   }
	
	
}
