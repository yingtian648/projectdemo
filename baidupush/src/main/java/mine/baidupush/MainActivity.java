package mine.baidupush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnOpen,btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startBaiduPush();
        initData();
    }

    private void initData() {
        if(PushManager.isPushEnabled(getApplicationContext())){
            Log.d("---------->","百度推送已开启");
        }else {
            Log.d("---------->","百度推送已关闭");
        }
    }

    private void initView() {
        btnOpen = (Button) findViewById(R.id.button2);
        btnClose = (Button) findViewById(R.id.button);
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    private void startBaiduPush() {
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"MN6fQ2SUYaeHkFFvQ7tTm7b4");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button){//关闭
            PushManager.stopWork(getApplicationContext());
        }else {//开启
            PushManager.resumeWork(getApplicationContext());
        }
    }
}
