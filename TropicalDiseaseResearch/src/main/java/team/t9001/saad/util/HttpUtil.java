package team.t9001.saad.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
public class HttpUtil {
    public static void post(String url, Map<String, Object> params) throws Exception{
        if (StringUtils.isBlank(url) || params == null) {
            return;
        }


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity entity = formatParams(params);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("请求" + url + " 失败:" + response.getStatusLine().getStatusCode());
        }
        String responseData = EntityUtils.toString(response.getEntity(), "utf-8").trim();
        System.out.println(responseData);
    }

    /**
     * 格式化参数
     * @param params
     * @return
     */
    private static UrlEncodedFormEntity formatParams(Map<String, Object> params) {
        List<NameValuePair> list = Lists.newArrayList();

        Set<Map.Entry<String, Object>> entrySet = params.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            list.add(new BasicNameValuePair(next.getKey(), String.valueOf(next.getValue())));
        }

        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(list, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
