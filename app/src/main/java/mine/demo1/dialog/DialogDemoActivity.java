package mine.demo1.dialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mine.demo1.R;
import mine.demo1.util.LogD;

/**
 * 自定义对话框样式
 */
public class DialogDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        button = (Button) findViewById(R.id.Button_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow();
            }
        });
    }

    private void dialogShow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.dialog_close_btn);
        TextView sure = (TextView)view.findViewById(R.id.dialog_sure);
        TextView cancel = (TextView)view.findViewById(R.id.dialog_cancel);
        imageView.setOnClickListener(this);
        sure.setOnClickListener(this);
        cancel.setOnClickListener(this);

        dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_close_btn:
                dialog.dismiss();
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            case R.id.dialog_sure:
                LogD.d("确定");
                break;
        }
    }
}
