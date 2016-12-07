package mine.demo1.circleprogress;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import mine.demo1.R;

/**
 * Created by Administrator on 2016/11/12.
 * 圆环进度条
 * 根据调用setProgress(int progress)方法的传入值
 * 画出圆环进度，及其中的颜色
 */
public class MyProgressbar extends View {
    private int circleColor, //进度颜色
            textColor,//文字颜色
            bgColor;//非进度部分圆环颜色
    private float circlewidth, //圆环的厚度
            textSize;//圆环的百分比文字颜色
    private int max;
    private int progress = 0;
    private String text = "0%";//进度条中间的百分比文字

    public MyProgressbar(Context context) {
        this(context, null);
    }

    public MyProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 初始化参数
     * 默认参数为（参照个人中心学习进度中的圆环进度）
     * 默认圆环背景色
     * 默认进度颜色
     * 默认文字颜色
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressbar);
        circleColor = typedArray.getColor(R.styleable.MyProgressbar_circleColor, context.getResources().getColor(R.color.progress_color));
        textColor = typedArray.getColor(R.styleable.MyProgressbar_textColor, context.getResources().getColor(R.color.progress_color));
        bgColor = typedArray.getColor(R.styleable.MyProgressbar_bgColor, context.getResources().getColor(R.color.progress_color_bg));
        circlewidth = typedArray.getDimension(R.styleable.MyProgressbar_circlewidth, 2);
        textSize = typedArray.getDimension(R.styleable.MyProgressbar_textSize, 2);
        max = typedArray.getInt(R.styleable.MyProgressbar_max, 100);
        progress = typedArray.getInt(R.styleable.MyProgressbar_progress, 20);
        typedArray.recycle();
    }

    /**
     * 核心方法
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //画背景
        int width = super.getWidth();
        int height = super.getHeight();
        int lineWidth = 0;//边长
        int lineXStart = 0;
        int lineYStart = 0;
        if (width > height) {
            lineWidth = height;
            lineXStart = (width - height) / 2;
        } else {
            lineWidth = width;
            lineYStart = (height - width) / 2;
        }
        /**
         * 取圆环外边的正方形
         * 距离外边方形10px【margin = 10px】
         */
        RectF rectF = new RectF((float) (lineXStart + 1 + circlewidth), (float) (lineYStart + 1 + circlewidth),
                (float) (lineWidth - 1 - circlewidth), (float) (lineWidth - 1 - circlewidth));
        paint.setStyle(Paint.Style.STROKE);//设置空心
        paint.setColor(bgColor);//设置圆环背景色
        paint.setStrokeWidth(circlewidth);//设置圆环宽度
        paint.setAntiAlias(true);
        float draw_arc = ((float) (progress) / max) * 360;//要画出的角度
        float start_arc = -90 + (360 - draw_arc);//起始位置
        canvas.drawArc(rectF, -90, (360 - draw_arc), false, paint);//画除去进度部分的弧度
        Log.e("--------------", start_arc + "弧度" + draw_arc + "弧度");
        paint.setColor(circleColor);    //设置进度颜色
        canvas.drawArc(rectF, start_arc, draw_arc, false, paint);//画进度弧度
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public synchronized int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        if (max < 0)
            throw new RuntimeException("max < 0");
        this.max = max;
    }

    public synchronized float getProgress() {
        return progress;
    }

    /**
     * 外部调用
     * 默认传入是一个百分比
     * 根据百分比画出对应的圆环和其中的百分比
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0)
            throw new RuntimeException("progress < 0");
        if (progress > max)
            progress = max;
        if (progress <= max)
            this.progress = progress;
        postInvalidate();//回调
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getCircleWidth() {
        return circlewidth;
    }

    public void setCircleWidth(float circlewidth) {
        this.circlewidth = circlewidth;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}