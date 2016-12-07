package mine.demo1.util;

import com.google.gson.Gson;

import mine.demo1.EApplication;

/**
 * Created by jh on 2016/11/14.
 * 从json字符串解析类
 */

public class GsonUtil {
    public static <T> T getResult(String JSONString, Class<T> classtype) {
        T t = null;
        try {
            Gson gson = EApplication.getGson();
            t = gson.fromJson(JSONString, classtype);
        } catch (Exception e) {
          e.printStackTrace();
        }

        return t;
    }
}
