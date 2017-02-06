package mine.twodimendemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult, tvResultFormat, tvUri;
    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.result);
        tvResultFormat = (TextView) findViewById(R.id.result1);
        tvUri = (TextView) findViewById(R.id.resulturi);
        findViewById(R.id.scan_utf_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击扫描二维码
                initActivity();
            }
        });
        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击扫描二维码
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (null != data && requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Log.d("---------->", "扫码完成");
                    tvResult.setText("扫描返回RESULT：\n" + data.getStringExtra(Intents.Scan.RESULT));
                    tvResultFormat.setText("扫描返回RESULT_FORMAT：\n" + data.getStringExtra(Intents.Scan.RESULT_FORMAT));
                    tvUri.setText("扫描返回URI：\n" + data.toUri(data.getFlags()));
                    break;
                default:
                    break;
            }
        }
    }

    private void initActivity() {
        callCapture("UTF-8");
    }

    private void callCapture(String characterSet) {
        int width = getScreenW(this) * 2 / 3;

        Intent intent = new Intent();
        intent.setAction(Intents.Scan.ACTION);
        // intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
        intent.putExtra(Intents.Scan.CHARACTER_SET, characterSet);
        intent.putExtra(Intents.Scan.WIDTH, width);
        intent.putExtra(Intents.Scan.HEIGHT, width);
        // intent.putExtra(Intents.Scan.PROMPT_MESSAGE, "type your prompt message");
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /** 获取屏幕宽度 */
    private int getScreenW(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int screenW = wm.getDefaultDisplay().getWidth();
        return screenW;
    }
}
