package mine.demo1.loadbackshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mine.demo1.R;

/**
 * Created by jh on 2016/12/1.
 * 加载为空
 * 加载失败
 * 重新加载
 */

public class LoadBackShowLayout {

    private View view1;

    /**
     * 加载失败后用户点击重新加载的回调接口
     */
    public interface ReloadOnLoadError {
        void reLoad();//点击重新加载回调
    }

    private ReloadOnLoadError reloadOnLoadError;
    private ViewGroup view;
    private RelativeLayout alertLayout;//提示布局

    private RelativeLayout alert;//提示子布局——重新加载/为空
    private LinearLayout loadlinear;//提示子布局——加载中
    private TextView tvMsg; //显示文本提示信息
    private Button btnReload;   //重新加载按钮
    private Context context;

    public LoadBackShowLayout(Context context,ViewGroup view, ReloadOnLoadError reloadOnLoadError) {
        this.view = view;
        this.reloadOnLoadError = reloadOnLoadError;
        this.context = context;
        initView();
    }

    /**
     * 加载控件
     */
    private void initView() {
        view1 = LayoutInflater.from(context).inflate(R.layout.loadback_show,view,false);
        view.addView(view1);
        tvMsg = (TextView) view1.findViewById(R.id.alert_box_textView);
        btnReload = (Button) view1.findViewById(R.id.alert_box_button);
        loadlinear = (LinearLayout) view1.findViewById(R.id.load_linear);
        alert = (RelativeLayout) view1.findViewById(R.id.alert_relative);
        tvMsg.setVisibility(View.GONE);
        btnReload.setVisibility(View.GONE);
        alert.setVisibility(View.GONE);
        loadlinear.setVisibility(View.GONE);
        //点击重新加载
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadOnLoadError.reLoad();
            }
        });
    }

    /**
     * 显示错误提示界面
     */
    public void showOnError(String msg){
        alert.setVisibility(View.VISIBLE);
        loadlinear.setVisibility(View.GONE);
        tvMsg.setVisibility(View.VISIBLE);
        tvMsg.setText(msg);
        btnReload.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空提示界面
     */
    public void showOnNull(){
        alert.setVisibility(View.VISIBLE);
        loadlinear.setVisibility(View.GONE);
        tvMsg.setVisibility(View.VISIBLE);
        tvMsg.setText("没有相关数据~~");
        btnReload.setVisibility(View.GONE);
    }

    /**
     * 显示加载界面
     */
    public void showOnLoading(){
        alert.setVisibility(View.GONE);
        loadlinear.setVisibility(View.VISIBLE);
    }

    /**
     * 显示加载完成[有数据]界面
     */
    public void showOnloadOk(){
        view.setVisibility(View.GONE);
    }

}
