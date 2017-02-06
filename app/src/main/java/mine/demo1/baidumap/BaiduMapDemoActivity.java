package mine.demo1.baidumap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Gradient;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import mine.demo1.R;

public class BaiduMapDemoActivity extends AppCompatActivity {

    @BindView(R.id.baidumapview)
    MapView mMapView;
    private LatLng point = new LatLng(39.963175, 116.400244);
    private BaiduMap mBaiduMap;
    private Marker marker;
    private BitmapDescriptor markerImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map_demo);
        ButterKnife.bind(this);
        init();
        initView();
        initData();
    }

    private void init() {
        mBaiduMap = mMapView.getMap();
        //构建Marker图标
        markerImg = BitmapDescriptorFactory.fromResource(R.drawable.place_red);
    }

    private void initView() {
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });
    }

    private void initData() {
        setDragMarker();
        drawPicture();
        setTextOptions();
        setHeatMap();
    }

    private void setHeatMap() {
        //设置渐变颜色值
        int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225,  0), Color.rgb(255, 0, 0) };
        //设置渐变颜色起始值
        float[] DEFAULT_GRADIENT_START_POINTS = { 0.2f, 1f };
        //构造颜色渐变对象
        Gradient gradient = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);


        //以下数据为随机生成地理位置点，开发者根据自己的实际业务，传入自有位置数据即可
        List<LatLng> randomList = new ArrayList<LatLng>();
        Random r = new Random();
        for (int i = 0; i < 500; i++) {
            // 116.220000,39.780000 116.570000,40.150000
            int rlat = r.nextInt(370000);
            int rlng = r.nextInt(370000);
            int lat = 39780000 + rlat;
            int lng = 116220000 + rlng;
            LatLng ll = new LatLng(lat / 1E6, lng / 1E6);
            randomList.add(ll);
        }

        //在大量热力图数据情况下，build过程相对较慢，建议放在新建线程实现
        HeatMap heatmap = new HeatMap.Builder()
                .data(randomList)
                .gradient(gradient)
                .build();
        //在地图上添加热力图
        mBaiduMap.addHeatMap(heatmap);
    }

    private void setTextOptions() {
        //定义文字所显示的坐标点
        LatLng llText = new LatLng(39.86923, 116.397428);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(24)
                .fontColor(0xFFFF00FF)
                .text("百度地图SDK")
                .rotate(-30)
                .position(llText);
        //在地图上添加该文字对象并显示
        mBaiduMap.addOverlay(textOption);
    }

    private void drawPicture() {
        //定义多边形的五个顶点
        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(39.91923, 116.327428);
        LatLng pt3 = new LatLng(39.89923, 116.347428);
        LatLng pt4 = new LatLng(39.89923, 116.367428);
        LatLng pt5 = new LatLng(39.91923, 116.387428);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        pts.add(pt5);
        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
        //在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);
    }

    private void setDragMarker() {
        OverlayOptions options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(markerImg)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
        //将marker添加到地图上
        marker = (Marker) (mBaiduMap.addOverlay(options));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
