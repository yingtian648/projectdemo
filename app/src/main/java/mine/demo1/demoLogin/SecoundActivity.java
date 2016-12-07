package mine.demo1.demoLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import mine.demo1.R;

public class SecoundActivity extends AppCompatActivity implements Animation.AnimationListener{
    private Animation animationIn;
    private Animation animationOut;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_secound);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        animationIn.setAnimationListener(this);
        animationOut.setAnimationListener(this);
        relativeLayout.startAnimation(animationIn);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == animationIn){
            relativeLayout.startAnimation(animationOut);
        }else {
            startActivity(new Intent(this,ThirdActivity.class));
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
