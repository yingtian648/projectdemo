package mine.demo1.demoLogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;

import mine.demo1.R;

/**
 * 测试淡入淡出跳转效果
 */
public class FirstActivity extends AppCompatActivity implements Animation.AnimationListener{
    private RelativeLayout relativeLayout;
    private Animation animationIn,animationOut;
    private ImageView imageView;
    int h,w,ih,iw;
    Bitmap res,show;
    private LinearLayout.LayoutParams params;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imageView.setImageBitmap(show);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        animationIn.setAnimationListener(this);
        animationOut.setAnimationListener(this);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setBackgroundResource(R.drawable.lead_bg1);
        getWindowWH();
    }

    private void getWindowWH(){
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 得到屏幕的长和宽
        w = dm.widthPixels;                //水平分辨率
        h = dm.heightPixels;          //垂直分辨率
        res = BitmapFactory.decodeResource(this.getResources(),R.drawable.lead_bg1);
        ih = res.getHeight();
        iw = res.getWidth();
        float imgScale = ((float) iw)/ih;
        float screenScale = ((float) w)/h;
        Log.e("-------------","img比例："+imgScale+"屏幕比例:"+screenScale);
        float scale = 0;
        if(imgScale>screenScale){
            scale = ((float) iw)/w;
        }else{
            scale = ((float) ih)/h;
        }
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(scale, scale);
        // 新得到的图片是原图片经过变换填充到整个屏幕的图片
        show = Bitmap.createBitmap(res, 0, 0,iw, ih, matrix, true);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == animationIn){
            relativeLayout.startAnimation(animationOut);
        }else {
            startActivity(new Intent(this,SecoundActivity.class));
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
