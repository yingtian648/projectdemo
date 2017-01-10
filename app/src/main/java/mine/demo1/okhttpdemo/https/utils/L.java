package mine.demo1.okhttpdemo.https.utils;

import android.util.Log;

/**
 * Created by zhy on 15/11/6.
 */
public class L
{
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("OkHttp", msg);
        }
    }

}

