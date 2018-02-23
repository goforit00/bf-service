package com.goforit.firstapple.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by goforit on 18/2/4.
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, params, "UTF-8");
    }

    public static String doGet(String url, Map<String, String> params, String charset) throws Exception {
        return doGet(url, params, (Map) null, charset);
    }

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers, String charset)
        throws Exception {
        HttpGet httpGet = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResp = null;

        String entry1;
        try {
            if (params != null && params.size() > 0) {
                StringBuilder e = (new StringBuilder(url)).append("?");
                Set statusCode = params.entrySet();
                Iterator respStr = statusCode.iterator();

                while (respStr.hasNext()) {
                    Map.Entry entry = (Map.Entry) respStr.next();
                    e.append((String) entry.getKey()).append("=")
                        .append(URLEncoder.encode((String) entry.getValue(), charset)).append("&");
                }

                url = e.substring(0, e.length() - 1);
            }

            httpGet = new HttpGet(url);
            if (headers != null) {
                Set e1 = headers.entrySet();
                Iterator statusCode1 = e1.iterator();

                while (statusCode1.hasNext()) {
                    Map.Entry respStr1 = (Map.Entry) statusCode1.next();
                    httpGet.addHeader((String) respStr1.getKey(), (String) respStr1.getValue());
                }
            }

            RequestConfig e2 =
                RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000)
                    .setConnectionRequestTimeout(20000).setStaleConnectionCheckEnabled(true).build();
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(e2);
            httpResp = httpClient.execute(httpGet);
            int statusCode2 = httpResp.getStatusLine().getStatusCode();
            if (statusCode2 != 200) {
                throw new RuntimeException("请求url:" + url + " 失败, status code=" + statusCode2);
            }

            String respStr2 = EntityUtils.toString(httpResp.getEntity(), charset);
            EntityUtils.consume(httpResp.getEntity());
            LOGGER.info("HttpUtil-doGet 请求url:{}, 返回结果:{}", url, respStr2);
            entry1 = respStr2;
        } catch (Exception var14) {
            LOGGER.error("HttpUtil-doGet 请求失败, url:{}, 参数:{}", url, params);
            throw new RuntimeException(var14);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }

            if (httpClient != null) {
                httpClient.close();
            }

            if (httpResp != null) {
                httpResp.close();
            }

        }

        return entry1;
    }
}
