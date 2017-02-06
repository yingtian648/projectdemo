package mine.demo1;

import android.app.Activity;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mine.demo1.util.CheckConnectBroadcast;
import mine.demo1.util.CrashHandle;

/**
 * Created by jh on 2016/11/7.
 */

public class EApplication extends LitePalApplication {
    private static Map<String, Object> dataMap = null;//用于存放常用数据
    private static Gson gson = null;//经常使用
    private static boolean isConnect = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        EApplication.isDebug = isDebug;
    }

    private static boolean isDebug = false;//判断是否联网
    private CheckConnectBroadcast checkConnectBroadcast = null;
    private static List<Activity> activities = new ArrayList<>();//放置已打开的activity

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
//        CrashHandle crashHandler = CrashHandle.getInstance();
//        // 注册crashHandler
//        crashHandler.init(getApplicationContext());
        dataMap = new HashMap<>();
        initHttps();
        initImageLoader();
        SDKInitializer.initialize(getApplicationContext());//初始化百度地图
    }

    //配置universal_Image_loader
    private void initImageLoader() {
        //1.配置图片显示信息
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,800)
                .threadPoolSize(5)//线程数
                .threadPriority(Thread.NORM_PRIORITY-1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
                .writeDebugLogs()
                .build();
        //2.将配置信息给予我们的ImageLoader对象
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public static void addActivities(Activity a) {
        EApplication.activities.add(a);
    }

    public static void clearActivities() {
        for (Activity a : activities) {
            if (a != null) {
                a.finish();
            }
        }
    }


    public static Map<String, Object> getDataMap() {
        return dataMap;
    }

    public static void setDataMap(Map<String, Object> dataMap) {
        EApplication.dataMap = dataMap;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        EApplication.gson = gson;
    }

    public static boolean isConnect() {
        return isConnect;
    }

    public static void setConnect(boolean connect) {
        isConnect = connect;
    }

    public void initHttps() {
        // 添加https证书
//        try {
//            OkHttpUtils.getInstance().setCertificates(new Buffer()
//                    .writeUtf8(Commconfig.CER)
//                    .inputStream()
//            );
//        } catch (Exception ioe) {
//            ioe.printStackTrace();
//        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
