package mine.demo1.okhttp_qcloud;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mine.demo1.R;
import mine.demo1.okhttp_qcloud.https.OkHttpUtils;
import mine.demo1.okhttp_qcloud.https.callback.StringCallback;

public class OkHttpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String[] names = {"成都", "资中", "资阳","福州"};
    private final String BaseUrl = "http://apis.baidu.com/apistore/weatherservice/citylist";
    private final String BaseUrl1 = "http://apis.baidu.com/apistore/weatherservice/weather";
    private final String apikey = "1b003ee9be62dfbef84e61ff07f54bc2";
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.tvShow)
    TextView tvShow;
    private List<String> data;
    private ArrayAdapter<String> adapter;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        spinner.setOnItemSelectedListener(this);
        spinner.setPopupBackgroundResource(R.color.course_num_after);
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            data.add(names[i]);
        }
        initAdapter();
        spinner.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getCity(data.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getCity(String placeName) {
        OkHttpUtils.get()
                .url(BaseUrl)
                .addHeader("apikey",apikey)
                .params(getparmas(placeName))
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("------------>", "城市_请求错误：" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.d("------------>", "城市_请求返回：" + response);
                OkCityResult result = gson.fromJson(response, OkCityResult.class);
                if (result != null && result.getErrNum() == 0 && result.getRetData() != null
                        && result.getRetData().size() > 0) {
                    OkCityResult.RetDataBean bean = result.getRetData().get(0);
                    getWeather(bean.getName_en());
                }
            }
        });
    }

    private void getWeather(String nameEn) {
        OkHttpUtils.get()
                .url(BaseUrl1)
                .addHeader("apikey",apikey)
                .params(getparmas1(nameEn))
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("------------>", "天气——请求错误：" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.d("------------>", "天气——请求返回：" + response);
                OkWeatherResult result = gson.fromJson(response, OkWeatherResult.class);
                if (result != null && result.getErrNum() == 0) {
                    OkWeatherResult.RetDataBean bean = result.getRetData();
                    if (bean == null) return;
                    tvShow.setText(
                            "城市："+bean.getCity()+"\n"+
                            "日期："+bean.getDate()+"\n"+
                            "经度："+bean.getLongitude()+"\n"+
                            "纬度："+bean.getLatitude()+"\n"+
                            "海拔："+bean.getAltitude()+"\n"+
                            "天气："+bean.getWeather()+"\n"+
                            "气温："+bean.getL_tmp()+"℃ ~ "+bean.getH_tmp()+"℃\n"+
                            "风力："+bean.getWS()+"\n"+
                            "风向："+bean.getWD()
                    );
                }
            }
        });
    }

    private Map<String, String> getparmas1(String citypinyin) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("citypinyin", citypinyin);
        return parmas;
    }

    private Map<String, String> getparmas(String placeName) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("cityname", placeName);
        return parmas;
    }
}
