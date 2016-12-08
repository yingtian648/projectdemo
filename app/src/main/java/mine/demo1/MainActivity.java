package mine.demo1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //跳转页面
    private Class[] classes = {CalenderTestActivity.class, CheckBoxInRadiogroupActivity.class, CircleProgressbarActivity.class
            , FirstActivity.class, Dialog.class, ChooseImgActivity.class, ImageProcessActivity.class, PullToRefreshActivity.class
            , TwoDimenCodeActivity.class, PagerPinchActivity.class};
    private String [] demoNames = {"日历","checkbox在radiogroup的事件","圆环进度条","页面跳转","无背景对话框"
            ,"头像选择","图片分区压缩","上拉加载|下拉刷新","二维码","viewPager中图片_可放大"};
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.mian_lv);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,demoNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void goToNextActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goToNextActivity(classes[position]);
    }
}
