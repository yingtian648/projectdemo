package mine.demo1.two_dimension_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mine.demo1.R;

/**
 * 二维码测试
 */
public class TwoDimenCodeActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textOpen,textShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_dimen_code);

        textOpen = (TextView)findViewById(R.id.twodimencode_opencamera);
        textShow = (TextView)findViewById(R.id.twodimencode_show);
    }

    @Override
    public void onClick(View v) {

    }
}
