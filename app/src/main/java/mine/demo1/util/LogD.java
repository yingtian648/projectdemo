package mine.demo1.util;

import android.util.Log;

/**
 * Created by jh on 2016/12/5.
 */

public class LogD {
    private static final String TAG = "---------->";
    public static void d(String msg){
        Log.d(TAG,msg);
    }
    public static void e(String msg){
        Log.e(TAG,msg);
    }
}
