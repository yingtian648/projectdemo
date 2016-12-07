package mine.demo1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by Ailn on 16/11/8.
 * 获取设备信息
 * 保存设备信息【shared】
 * 保存接入app时系统返回值
 * 获取屏幕宽高
 * 获取系统时间
 * 获取时间格式[00:00:00   11/25 17:34]
 * sp.dp.px转换
 * 手机号码 密码正则匹配
 * 获取日期[09Nov.]
 */
public class Tools {

    /**
     * 判断是否的DEBUG模式
     * 当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }


    /**
     * 设备唯一序列号
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(context.TELEPHONY_SERVICE);
        String deviceId = "";
        try {
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();


        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = getDeviceUUID(context);
        }
         if (TextUtils.isEmpty(deviceId)) {
            saveDeviceUUID(context, UUID.randomUUID().toString());
            deviceId = getDeviceUUID(context);
        }
        return deviceId;
    }


    public static String getDeviceUUID(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("mUUID", "");

    }

    /**
     * 存入token
     */
    public static void saveDeviceUUID(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("mUUID", token);
        edit.commit();

    }

    public static synchronized String getHeaderKey(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("adas_app_key_et", "");

    }

    /**
     * 存入token
     */
    public static void saveHeaderKey(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("adas_app_key_et", token);
        edit.commit();

    }

    public static String getServerPath(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("serverPath", "");

    }

    /**
     * 存入token
     */
    public static void saveServerPath(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("serverPath", token);
        edit.commit();

    }


    public static String getSecretKey(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("SecretKey", "");
    }

    /**
     * SecretKey
     */
    public static void saveSecretKey(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("SecretKey", token);
        edit.commit();

    }

    public static String getSecretId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("SecretId", "");

    }

    /**
     * 存入SecretId
     */
    public static void saveSecretId(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("SecretId", token);
        edit.commit();

    }


    public static String getDefaultRegion(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        return sp.getString("DefaultRegion", "");

    }

    /**
     * DefaultRegion
     */
    public static void saveDefaultRegion(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("DefaultRegion", token);
        edit.commit();

    }

    /**
     * 保存是否已发送手机信息
     */
    public static void saveIsSendMobileInfo(Context context, boolean isSend) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isSendMobileInfo_key", isSend);
        edit.commit();
    }

    /**
     * 获取是否已发送手机信息
     *
     * @param context
     * @return
     */
    public static boolean isSendMobileInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getBoolean("isSendMobileInfo_key", false);
    }

    /**
     * 保存日志发送状态——发送后保存true,发送失败保存false
     * @param context
     * @param isSend
     */
    public void saveSendLogState(Context context, boolean isSend){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putBoolean("SendLogState",isSend).commit();
    }

    /**
     * 获取日志是否已发送——默认未发送
     * @param context
     */
    public void getSendLogState(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.getBoolean("SendLogState",false);
    }

    /**
     * 保存home是否启动引导
     * @param context
     */
    public static void saveNeedHomeLeadShow(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putBoolean("isNeedHomeLeadShow", true).commit();
    }

    /**
     * 获取是否需要引导
     * @param context
     * @return
     */
    public static boolean isNeedHomeLeadShow(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getBoolean("isNeedHomeLeadShow", false);
    }

    /**
     * 保存course是否启动引导
     * @param context
     */
    public static void saveNeedCourseLeadShow(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putBoolean("isNeedCourseLeadShow", true).commit();
    }

    /**
     * 获取是否需要引导
     * @param context
     * @return
     */
    public static boolean isNeedCourseLeadShow(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getBoolean("isNeedCourseLeadShow", false);
    }

    /**
     * 获取网络状态
     * 在leadactivity开启时presenter层调用
     *
     * @return
     */
    public static boolean getInternetState(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getScreenW(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int screenW = wm.getDefaultDisplay().getWidth();
        return screenW;

    }

    public static int getScreenH(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int screenH = wm.getDefaultDisplay().getHeight();
        return screenH;
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss 格式时间
     */
    public static String getCurrentTime() {
        long sd = System.currentTimeMillis();
        Date dat = new Date(sd);

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String sb = format.format(gc.getTime());

        return sb;

    }

    /**
     * @return yyyy-MM-dd HH:mm:ss 格式时间
     */
    public static String getDateString(String d) {

        if (TextUtils.isEmpty(d)) {
            return "0000-00-00";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sb = "";
        try {
            Date date = sdf.parse(d);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            sb = format.format(gc.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sb;

    }

    /**
     * 获取日期格式【如：10/25 19:21】
     *
     * @param dateString 完整日期格式
     * @return
     */
    public static String getSimpleDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            return sdf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            LogD.d("Tools.getSimpleDate--日期转换错误");
        }
        return dateString;
    }

    /**
     * 获取日期格式【如：2016-10-11】
     *
     * @param dateString 完整日期格式
     * @return
     */
    public static String getDateYMD(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            return sdf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            LogD.d("Tools.getSimpleDate--日期转换错误");
        }
        return dateString;
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    /**
     * 隐藏输入法[输入键盘]
     * 显示输入法[输入键盘]
     */
    public static void hideInputkeybord(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //如果开启,关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示输入法[输入键盘]
     */
    public static void showInputkeybord(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果开启,关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        imm.showSoftInput(view, InputMethodManager.HIDE_NOT_ALWAYS);
    }

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

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 密码正则匹配规则
     * 6到11位只能有字符，数字，下划线
     *
     * @param pwd
     * @return
     */
    public static boolean isRightPwd(String pwd) {
        String regEx = "^[a-zA-Z0-9]{6,12}$";
        return pwd.matches(regEx);
    }

    /**
     * 手机号码正则匹配规则
     * 13位数字
     *
     * @param pwd
     * @return
     */
    public static boolean isRightTelephoneNum(String pwd) {
        String REGEX_PHONE = "^1[3456789][0-9]{9}$";
        return pwd.matches(REGEX_PHONE);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    /**
     * 视频播放时长格式化
     *
     * @param time
     * @return
     */

    public static String generateTime(int time) {
        int seconds = time % 60;
        int minutes = (time / 60) % 60;
        int hours = time / 3600;
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 获取当前的月份的String
     *
     * @return
     */
    public static String getMonthString() {
        String[] monString = {"Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."};
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return monString[month];
    }

    /**
     * 获取当前日期的天String
     *
     * @return
     */
    public static String getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10)
            return "0" + day;
        return day + "";
    }

    /**
     * 获取哲言
     */
    public static String getLeadWords(Context context) {
        int x = (int) (Math.random() * WisdomUtil.wisdoms.length);
        saveWisdomIndex(context, x);
        return WisdomUtil.wisdoms[x];
    }

    /**
     * 获取哲言
     */
    public static String getLoginWords(Context context) {
        int x = getWisdomIndex(context);
        return WisdomUtil.wisdoms[x];
    }

    /**
     * 存放哲言的下标
     */
    public static void saveWisdomIndex(Context context, int index) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("WisdomIndex", index);
        edit.commit();

    }

    /**
     * 获取哲言的下标
     */
    public static int getWisdomIndex(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getInt("WisdomIndex", 0);
    }
}
