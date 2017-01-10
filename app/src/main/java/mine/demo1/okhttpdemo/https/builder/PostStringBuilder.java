package mine.demo1.okhttpdemo.https.builder;

import android.content.Context;
import android.text.TextUtils;

import com.squareup.okhttp.MediaType;

import java.util.IdentityHashMap;
import java.util.Map;

import mine.demo1.okhttpdemo.https.request.PostStringRequest;
import mine.demo1.okhttpdemo.https.request.RequestCall;
import mine.demo1.util.Tools;

/**
 * Created by zhy on 15/12/14.
 */
public class PostStringBuilder extends OkHttpRequestBuilder
{
    public static final String HEADER_KEY = "adas_app_key_et";
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }


    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType).build();
    }

    @Override
    public PostStringBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PostStringBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PostStringBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostStringBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new IdentityHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostStringBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public PostStringBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new IdentityHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
    //添加默认header
    public PostStringBuilder addDefaultHeader(Context context)
    {
        if (this.headers == null)
        {
            headers = new IdentityHashMap<>();
        }
        if (!TextUtils.isEmpty(Tools.getHeaderKey(context))){
            headers.put(HEADER_KEY, Tools.getHeaderKey(context));
            headers.put("charset", "UTF-8");
        }
        return this;
    }
}
