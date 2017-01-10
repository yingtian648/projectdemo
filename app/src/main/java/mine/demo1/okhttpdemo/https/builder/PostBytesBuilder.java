package mine.demo1.okhttpdemo.https.builder;

import com.squareup.okhttp.MediaType;

import java.util.IdentityHashMap;
import java.util.Map;

import mine.demo1.okhttpdemo.https.request.PostBytesRequest;
import mine.demo1.okhttpdemo.https.request.RequestCall;

/**
 * Created by zhy on 15/12/14.
 */
public class PostBytesBuilder extends OkHttpRequestBuilder
{
    private byte[] content;
    private MediaType mediaType;


    public PostBytesBuilder content(byte[] content)
    {
        this.content = content;
        return this;
    }

    public PostBytesBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }


    @Override
    public RequestCall build()
    {
        return new PostBytesRequest(url, tag, params, headers, content, mediaType).build();
    }

    @Override
    public PostBytesBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PostBytesBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PostBytesBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostBytesBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new IdentityHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostBytesBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public PostBytesBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new IdentityHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
