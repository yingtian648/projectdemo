package mine.demo1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import mine.demo1.R;
import mine.demo1.calender.CalenderTestActivity;
import mine.demo1.checkbox_in_radiogroup.CheckBoxInRadiogroupActivity;
import mine.demo1.circleprogress.CircleProgressbarActivity;
import mine.demo1.demoLogin.FirstActivity;
import mine.demo1.head.ChooseImgActivity;
import mine.demo1.imageprocess.ImageProcessActivity;
import mine.demo1.pulltorefresh.PullToRefreshActivity;
import mine.demo1.two_dimension_code.TwoDimenCodeActivity;
import mine.demo1.viewpager_pinchimageview.PagerPinchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //按钮
    private int[] ids = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6
            , R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11};
    //跳转页面
    private Class [] classes = {CalenderTestActivity.class,CheckBoxInRadiogroupActivity.class,CircleProgressbarActivity.class
    ,FirstActivity.class,Dialog.class,ChooseImgActivity.class,ImageProcessActivity.class,PullToRefreshActivity.class
    ,TwoDimenCodeActivity.class,PagerPinchActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < ids.length; i++) {
            findViewById(ids[i]).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < ids.length; i++) {
            if (v.getId() == ids[i]) {
                goToNextActivity(classes[i]);
            }
        }
    }
    private void goToNextActivity(Class activityClass){
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
