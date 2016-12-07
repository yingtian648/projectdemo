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
    private int[] ids = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6
            , R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11};
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
        Class activityClass = null;
        switch (v.getId()) {
            case R.id.button1:
                activityClass = CalenderTestActivity.class;
                break;
            case R.id.button2:
                activityClass = CheckBoxInRadiogroupActivity.class;
                break;
            case R.id.button3:
                activityClass = CircleProgressbarActivity.class;
                break;
            case R.id.button4:
                activityClass = FirstActivity.class;
                break;
            case R.id.button5:
                activityClass = Dialog.class;
                break;
            case R.id.button6:
                activityClass = ChooseImgActivity.class;
                break;
            case R.id.button7:
                activityClass = ImageProcessActivity.class;
                break;
            case R.id.button8:
                activityClass = PullToRefreshActivity.class;
                break;
            case R.id.button9:
                activityClass = TwoDimenCodeActivity.class;
                break;
            case R.id.button10:
                activityClass = PagerPinchActivity.class;
                break;
            case R.id.button11:
                break;
        }
        goToNextActivity(activityClass);
    }
    private void goToNextActivity(Class activityClass){
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
