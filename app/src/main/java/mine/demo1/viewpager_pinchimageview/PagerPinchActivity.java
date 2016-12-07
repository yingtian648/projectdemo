package mine.demo1.viewpager_pinchimageview;

import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mine.demo1.R;
import mine.demo1.adapter.PagerPinchAdapter;
import mine.demo1.imageprocess.PinchImageView;

public class PagerPinchActivity extends AppCompatActivity{
    private ImageView imageView;
    private TextView tvTitle;
    private PinchImageViewPager pinchPager;
    private int[] imgIDs = {R.drawable.lead_bg1,R.drawable.lead_bg2,R.drawable.lead_bg3};
    private PinchImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_pinch);

        tvTitle = (TextView) findViewById(R.id.tv_indicator);
        tvTitle.setText("1/"+Global.getTestImagesCount());
        imageView = (ImageView) findViewById(R.id.iv_back);
        pinchPager = (PinchImageViewPager) findViewById(R.id.vp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        final LinkedList<PinchImageView> viewCache = new LinkedList<PinchImageView>();
        final DisplayImageOptions thumbOptions = new DisplayImageOptions.Builder().resetViewBeforeLoading(true).cacheInMemory(true).build();
        final DisplayImageOptions originOptions = new DisplayImageOptions.Builder().build();
        pinchPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Global.getTestImagesCount();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PinchImageView piv;
                if (viewCache.size() > 0) {
                    piv = viewCache.remove();
                    piv.reset();
                } else {
                    piv = new PinchImageView(PagerPinchActivity.this);
                }
                ImageSource image = Global.getTestImage(position);
                Global.getImageLoader(getApplicationContext()).displayImage(image.getUrl(100, 100), piv, thumbOptions);
                container.addView(piv);
                return piv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                container.removeView(piv);
                viewCache.add(piv);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                ImageSource image = Global.getTestImage(position);
                Global.getImageLoader(getApplicationContext()).displayImage(image.getUrl(image.getOriginWidth(), image.getOriginHeight()), piv, originOptions);
                pinchPager.setMainPinchImageView(piv);
            }
        });

        pinchPager.setOnPageChangeListener(new PinchImageViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText((position+1)+"/"+Global.getTestImagesCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
