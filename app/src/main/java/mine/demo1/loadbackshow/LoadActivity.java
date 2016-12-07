package mine.demo1.loadbackshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import mine.demo1.R;

/**
 * 加载回调类[含布局]外层最好是relative
 */
public class LoadActivity extends AppCompatActivity implements View.OnClickListener , LoadBackShowLayout.ReloadOnLoadError {

    private Button load,nullBack,errorBack;
    private RelativeLayout viewGroup;
    private LoadBackShowLayout loadBackShowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        load = (Button) findViewById(R.id.button2);
        viewGroup = (RelativeLayout) findViewById(R.id.rela_load);
        nullBack = (Button) findViewById(R.id.button3);
        errorBack = (Button) findViewById(R.id.button4);
        loadBackShowLayout = new LoadBackShowLayout(this,viewGroup,this);

        nullBack.setOnClickListener(this);
        errorBack.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                Log.e("--------------","加载");
                loadBackShowLayout.showOnLoading();
                break;
            case R.id.button3:
                loadBackShowLayout.showOnNull();
                break;
            case R.id.button4:
                loadBackShowLayout.showOnError("内部错误！");
                break;
        }
    }

    @Override
    public void reLoad() {
        Log.e("--------------","点击了重新加载加载");
    }
}
