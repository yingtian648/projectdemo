package mine.baidupush.baidupush;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/**
 * Created by jh on 2016/12/12.
 */

public class BaiduPushCallBackReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        Log.d("---------->","onBind");
    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        Log.d("---------->","onUnbind");
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.d("---------->","onSetTags");
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.d("---------->","onDelTags");
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        Log.d("---------->","onListTags");
    }

    /**
     * 接收渗透消息【不会出现在通知栏】
     * @param context
     * @param s
     * @param s1
     */
    @Override
    public void onMessage(Context context, String s, String s1) {
        Log.d("---------->","onMessage:s——"+s+"\ts1——"+s1);
    }

    /**
     * 接收标题，内容【点击时调用】
     * @param context
     * @param s 标题
     * @param s1 内容
     * @param s2 附加字段
     */
    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.d("---------->","onNotificationClicked:s——"+s+"\ts1——"+s1+"\ts2——"+s2);
    }

    /**
     * 接收附加字段【到达时调用】
     * @param context
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        Log.d("---------->","onNotificationArrived:s——"+s+"\ts1——"+s1+"\ts2——"+s2);
    }
}
