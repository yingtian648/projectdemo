package mine.demo1.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import mine.demo1.EApplication;

/**
 * Created by jh on 2016/11/9.
 * 用于接收网络状态的广播
 */
public class CheckConnectBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//2G/3G/4G链接
        NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//WIFI链接
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            //判断用户手机没有联网
            EApplication.setConnect(false);
        }else {
            //判断用户手机已经联网
            EApplication.setConnect(true);
        }
    }
}
