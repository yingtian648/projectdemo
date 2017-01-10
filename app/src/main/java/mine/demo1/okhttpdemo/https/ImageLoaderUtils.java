package mine.demo1.okhttpdemo.https;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import mine.demo1.R;

/**
 * Created by Ailn on 16/11/12.
 */
public class ImageLoaderUtils {


    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.drawable.img)
                .error(R.drawable.img).crossFade().into(imageView);
    }

    /**
     * 获取网络图片的bitmap【少量的时候可用】
     * @param urlString
     * @return
     */
    public static Bitmap getBitmapOnUrl(String urlString){
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
