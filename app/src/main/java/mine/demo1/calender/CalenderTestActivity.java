package mine.demo1.calender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

import mine.demo1.R;

/**
 * 日历相关类
 */
public class CalenderTestActivity extends AppCompatActivity {
    private TextView day,mon,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_test);
        Log.e("--------------","当前日期："+getDayOfMonth()+getMonthString());
        day = (TextView) findViewById(R.id.lead_day);
        mon = (TextView) findViewById(R.id.lead_month);
        content = (TextView) findViewById(R.id.lead_content);

        day.setText(getDayOfMonth());
        mon.setText(getMonthString());
        content.setText("一切生命都会有他的起源，也会有它的未来。");
    }
    /**
     * 获取当前的月份的String
     * @return
     */
    public String getMonthString(){
        String [] monString = {"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sep.","Oct.","Nov.","Dec."};
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return monString[month];
    }

    /**
     * 获取当前日期的天String
     * @return
     */
    public String getDayOfMonth(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(day<10)
            return "0"+day;
        return day+"";
    }
}
