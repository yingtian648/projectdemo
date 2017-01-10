package mine.demo1.styledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mine.demo1.R;

public class StyleDemoActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_demo);
        button = (Button) findViewById(R.id.styledemo_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
