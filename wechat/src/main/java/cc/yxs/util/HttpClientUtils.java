package cc.yxs.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author jie.xu
 * @date 2016年1月19日 下午1:59:24
 */
public class HttpClientUtils {

  private static final int connectTimeout = 30000;

  private HttpClientUtils() {

  }

  public static HttpClientUtils build() {
    return new HttpClientUtils();
  }

  public String get(String url) throws Exception {
    return Request.Get(url).connectTimeout(connectTimeout).execute().returnContent().asString();
  }

  public String get(String url, List<NameValuePair> params) throws Exception {
    return Request.Get(new URIBuilder(url).addParameters(params).build()).connectTimeout(connectTimeout).execute().returnContent().asString();
  }


  public String post(String url, List<NameValuePair> params) throws IOException {
    return Request.Post(url).bodyForm(params, Consts.UTF_8).connectTimeout(connectTimeout).execute().returnContent().asString();
  }

  public String post(String url, NameValuePair... params) throws ClientProtocolException, IOException {
    return Request.Post(url).bodyForm(Arrays.asList(params), Consts.UTF_8).connectTimeout(connectTimeout).execute().returnContent().asString();
  }

  public String post(String url, String bodyStr) throws IOException {
    return Request.Post(url).body(new StringEntity(bodyStr, "UTF-8")).connectTimeout(connectTimeout).execute().returnContent().asString();
  }



  public String postJson(String url, String body) throws IOException {
    return Request.Post(url).addHeader("Content-Type", "application/json").body(new StringEntity(body, "UTF-8")).connectTimeout(connectTimeout).execute().returnContent()
        .asString();
  }

  public String uploadFile(String url, String fileFiled, String fileName, InputStream in) throws Exception {
    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
    multipartEntityBuilder = multipartEntityBuilder.addBinaryBody(fileFiled, in, ContentType.create("multipart/form-data", Consts.UTF_8), fileName);
    multipartEntityBuilder.setCharset(Consts.UTF_8);
    multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
    HttpEntity e = multipartEntityBuilder.build();
    return Request.Post(url).body(e).connectTimeout(connectTimeout).execute().returnContent().asString();
  }



  public String postWithAuthHeader(String url, String authHeader, String body) throws IOException {
    return Request.Post(url).addHeader("Authorization", authHeader).addHeader("Content-Type", "application/json").body(new StringEntity(body, "UTF-8"))
        .connectTimeout(connectTimeout).execute().returnContent().asString();
  }
}
