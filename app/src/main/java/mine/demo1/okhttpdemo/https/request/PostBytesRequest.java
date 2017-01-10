package mine.demo1.okhttpdemo.https.request;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import mine.demo1.okhttpdemo.https.utils.Exceptions;

/**
 * Created by zhy on 15/12/14.
 */
public class PostBytesRequest extends OkHttpRequest
{
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("application/octet-stream");

    private byte[] content;
    private MediaType mediaType;


    public PostBytesRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, byte[] content, MediaType mediaType)
    {
        super(url, tag, params, headers);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null)
        {
            Exceptions.illegalArgument("the Content can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    @Override
    protected RequestBody buildRequestBody()
    {
        return RequestBody.create(mediaType, content);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }

}
