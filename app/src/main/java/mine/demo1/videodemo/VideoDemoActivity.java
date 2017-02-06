package mine.demo1.videodemo;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mine.demo1.R;
import mine.demo1.util.LogD;

public class VideoDemoActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.videobtn)
    Button videobtn;
    //语音文件保存路径
    private String FileName = null;

    @BindView(R.id.videotext)
    TextView videotext;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_demo);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        videobtn.setOnTouchListener(this);
    }

    private void initData() {
        //设置sdcard的路径
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName += "/audioTest.amr";

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(FileName);
        mRecorder.setMaxDuration(3000);//设置最长录制时间
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        Glide.with(this).load(R.drawable.bike_ic).asGif().into(img);
    }


    @OnClick({R.id.videobtn2, R.id.videobtn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.videobtn2://开始播放
                LogD.d("开始播放");
                // TODO Auto-generated method stub
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(FileName);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    LogD.d("播放失败");
                }
                break;
            case R.id.videobtn3://结束播放
                if (mPlayer.isPlaying()) {
                    LogD.d("正在播放");
                }
                LogD.d("播放结束");
                // TODO Auto-generated method stub
                mPlayer.release();
                mPlayer = null;
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.videobtn && event.getAction() == MotionEvent.ACTION_DOWN) {
            LogD.d("开始录音");
            img.setVisibility(View.VISIBLE);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                LogD.d("prepare() failed");
            }
            mRecorder.start();
        }
        if (v.getId() == R.id.videobtn && event.getAction() == MotionEvent.ACTION_UP) {
            LogD.d("录音结束");
            img.setVisibility(View.GONE);
            // TODO Auto-generated method stub
            mRecorder.stop();
            mRecorder.reset();
        }
        return false;
    }
}
