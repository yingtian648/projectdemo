package mine.demo1.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import java.util.TreeMap;

import mine.demo1.R;
import mine.demo1.okhttpdemo.qcloud.Module.Cvm;
import mine.demo1.okhttpdemo.qcloud.QcloudApiModuleCenter;

public class OKHttpDemoActivity extends AppCompatActivity {
    private String fileId = "14651978969499372890";
    private String baseUrl = "vod.api.qcloud.com";
    private String interfaceName = "DescribeVodPlayUrls";
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);
        initData();
    }

    private void initData() {
        getVideoInfo();
    }


    public void getVideoInfo() {
        new Thread() {
            @Override
            public void run() {
                 /* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
                TreeMap<String, Object> config = new TreeMap<String, Object>();
                config.put("SecretId", "AKIDov2S69WJS2foNONZZFLcTurUYtv3OHOm");
                config.put("SecretKey", "kBIoQC1UONsnnOTPK53mutNEips74Rv4");
                /* 请求方法类型 POST、GET */
                config.put("RequestMethod", "GET");
                /* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
                config.put("DefaultRegion", "gz");
                Log.d("------------>", "请求参数" + gson.toJson(config));
                /*
                 * 你将要使用接口所在的模块，可以从 官网->云api文档->XXXX接口->接口描述->域名
                 * 中获取，比如域名：cvm.api.qcloud.com，module就是 new Cvm()。
                 */
                /*
                 * 示例：DescribeInstances
                 * 的api文档地址：http://www.qcloud.com/wiki/v2/DescribeInstances
                 */
                QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cvm(), config);

                TreeMap<String, Object> params = new TreeMap<>();
                /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
                /* DescribeInstances 接口的部分可选参数如下 */
                params.put("fileId", fileId);
                /* generateUrl方法生成请求串,可用于调试使用 */
                //System.out.println(module.generateUrl("DescribeInstances", params));
                Log.d("------------>", "请求参数" + gson.toJson(params));
                String result = null;
                try {
			    /* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
                    result = module.call(interfaceName, params);
                    Log.d("------------>", "请求返回：" + result);
                } catch (Exception e) {
                    Log.d("------------>", "请求出错：" + e.getMessage());
                }
            }
        }.start();
    }
}
