package mine.demo1.imageprocess;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import mine.demo1.R;
import mine.demo1.util.LogD;

public class ImageProcessActivity extends AppCompatActivity implements View.OnClickListener
        ,View.OnLongClickListener{
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;//手机相册返回
    private static final int CODE_RESULT_REQUEST = 0xa2;
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 400;
    private static int output_Y = 400;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "testImage.jpg";

    Button button;
    ImageView imageView;
    TextView textView;
    DragViewGroup dragViewGroup;
    private DisplayMetrics dm;
    private int lastX;
    private int lastY;
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);
        dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels;

        button = (Button) findViewById(R.id.choose_img);
        button.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.img_img);
        textView = (TextView) findViewById(R.id.imgprecess_tv);
        imageView.setOnLongClickListener(this);
        setGifIntoImageView();
    }

    private void setGifIntoImageView() {
        //可以使用任何ImageView支持的方式设置图片
        imageView.setImageResource(R.drawable.lead_bg1);//可双击2.5倍放大，可滑动
        //or 你还能使用第三方图片加载库加载图片，如ImageLoader
//        imageLoader.displayImage("http://host.com/my_pic.jpg", imageView);

    }

    @Override
    public void onClick(View v) {
        choseHeadImageFromGallery();
    }
    @Override
    public boolean onLongClick(View v) {

        return false;
    }


    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        //相册选中照片后返回
        if(requestCode == CODE_GALLERY_REQUEST){
            Bitmap bitmap = getBitmapFromUri(intent.getData());
            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private Bitmap getBitmapFromUri(Uri uri)
    {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            LogD.d("bitmap大小："+bitmap.getByteCount()+"\t目录为：" + uri);
            LogD.d("bigmap的宽高："+bitmap.getWidth()+"*"+bitmap.getHeight());
            return bitmap;
        }
        catch (Exception e)
        {
            LogD.d(e.getMessage());
            LogD.d("目录为：" + uri);
            e.printStackTrace();
            return null;
        }
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

}
