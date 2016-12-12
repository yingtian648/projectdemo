package mine.demo1;

import android.app.Dialog;
import android.content.Context;
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

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

import java.util.ArrayList;
import java.util.List;

import mine.demo1.R;
import mine.demo1.calender.CalenderTestActivity;
import mine.demo1.checkbox_in_radiogroup.CheckBoxInRadiogroupActivity;
import mine.demo1.circleprogress.CircleProgressbarActivity;
import mine.demo1.demoLogin.FirstActivity;
import mine.demo1.head.ChooseImgActivity;
import mine.demo1.imageprocess.ImageProcessActivity;
import mine.demo1.notification_push.JiGuangNotificationActivity;
import mine.demo1.notification_push.XinGeNotificationActivity;
import mine.demo1.pulltorefresh.PullToRefreshActivity;
import mine.demo1.two_dimension_code.TwoDimenCodeActivity;
import mine.demo1.util.LogD;
import mine.demo1.viewpager_pinchimageview.PagerPinchActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //跳转页面
    private Class[] classes = {CalenderTestActivity.class, CheckBoxInRadiogroupActivity.class, CircleProgressbarActivity.class
            , FirstActivity.class, Dialog.class, ChooseImgActivity.class, ImageProcessActivity.class, PullToRefreshActivity.class
            , TwoDimenCodeActivity.class, PagerPinchActivity.class, XinGeNotificationActivity.class, JiGuangNotificationActivity.class
    };
    private String[] demoNames = {"日历", "checkbox在radiogroup的事件", "圆环进度条", "页面跳转", "无背景对话框"
            , "头像选择", "图片分区压缩", "上拉加载|下拉刷新", "二维码", "viewPager中图片_可放大", "腾讯信鸽", "极光推送"};
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.mian_lv);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, demoNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setXinGePush();//完成信鸽服务的启动与APP注册过程
    }

    /**
     * 完成信鸽服务的启动与APP注册过程
     */
    private void setXinGePush() {
        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(this, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        Context context = getApplicationContext();
        XGPushManager.registerPush(context);
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                LogD.d("信鸽注册成功！" + o.toString());
            }

            @Override
            public void onFail(Object o, int i, String s) {
                LogD.d("信鸽注册失败！" + s);
            }
        });

        // 2.36（不包括）之前的版本需要调用以下2行代码
        Intent service = new Intent(context, XGPushService.class);
        context.startService(service);

        // 其它常用的API：
        // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
        // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
        // 反注册（不再接收消息）：unregisterPush(context)
        // 设置标签：setTag(context, tagName)
        // 删除标签：deleteTag(context, tagName)
    }

    private void goToNextActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goToNextActivity(classes[position]);
    }
}
