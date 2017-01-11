package mine.demo1.okhttp_qcloud.https.builder;

import android.content.Context;
import android.text.TextUtils;

import com.squareup.okhttp.MediaType;

import java.io.File;
import java.util.IdentityHashMap;
import java.util.Map;

import mine.demo1.okhttp_qcloud.https.request.PostFileRequest;
import mine.demo1.okhttp_qcloud.https.request.RequestCall;
import mine.demo1.util.Tools;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFileBuilder extends OkHttpRequestBuilder
{
    public static final String HEADER_KEY = "adas_app_key_et";
    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }


    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType).build();
    }

    @Override
    public PostFileBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PostFileBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFileBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostFileBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new IdentityHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFileBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public PostFileBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new IdentityHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    //添加默认header
    public PostFileBuilder addDefaultHeader(Context context)
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
