package mine.demo1.notification_push;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mine.demo1.R;

public class JiGuangNotificationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_guang_notification);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.open).setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.open){
        }
        if(v.getId()==R.id.close){
        }
    }
}
