package mine.demo1.checkbox_in_radiogroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import mine.demo1.R;
import mine.demo1.util.LogD;

/**
 * 测试RadioGroup中放RadioButton,CheckBox及其监听事件
 */
public class CheckBoxInRadiogroupActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener,CompoundButton.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_in_radiogroup);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        checkBox1 = (CheckBox)findViewById(R.id.check1);
        checkBox2 = (CheckBox)findViewById(R.id.check2);
        checkBox3 = (CheckBox)findViewById(R.id.check3);
        checkBox4 = (CheckBox)findViewById(R.id.check4);

        setListener();
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(this);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.check1:
                LogD.d("群check1");
                break;
            case R.id.check2:
                LogD.d("群check2");
                break;
            case R.id.check3:
                LogD.d("群check3");
                break;
            case R.id.check4:
                LogD.d("群check3");
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.check1:
                LogD.d("check1----");
                break;
            case R.id.check2:
                LogD.d("check2----");
                break;
            case R.id.check3:
                LogD.d("check3----");
                break;
            case R.id.check4:
                LogD.d("check3----");
                break;
        }
    }
}
