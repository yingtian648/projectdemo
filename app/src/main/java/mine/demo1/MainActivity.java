package mine.demo1;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText;
    Button button,btnshow;
    RelativeLayout scrollView;
    private RelativeLayout activityrelative;
    int softHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.et);
        button = (Button) findViewById(R.id.btn_submit);
        btnshow = (Button) findViewById(R.id.btnshow);
        btnshow.setOnClickListener(this);
        scrollView = (RelativeLayout)findViewById(R.id.sv);
        activityrelative = (RelativeLayout)findViewById(R.id.activity_main);
        activityrelative.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                Rect r = new Rect();
                activityrelative.getWindowVisibleDisplayFrame(r);

                int screenHeight = activityrelative.getRootView().getHeight();
                softHeight = screenHeight - (r.bottom - r.top);
                Log.e("Keyboard Size", "Size: " + softHeight);

                //boolean visible = heightDiff > screenHeight / 3;
            }
        });
    }

    @Override
    public void onClick(View v) {
        scrollView.setVisibility(View.VISIBLE);
    }

}
