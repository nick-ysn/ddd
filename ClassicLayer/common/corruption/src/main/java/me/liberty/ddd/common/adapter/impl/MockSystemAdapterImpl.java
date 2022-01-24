package me.liberty.ddd.common.adapter.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author yuanshouna@gmail.com
 * @created 2022-01-20 19:26
 */
public class MockSystemAdapterImpl {

    private HttpClient httpClient;

    public MockResult create (String mockId) throws IOException {
        HttpPost httpPost = new HttpPost("https://xxx");
        httpPost.setEntity(new StringEntity(MockTranslator.toCreateRequest(mockId)));
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("请求下游系统失败" + response.getStatusLine().getStatusCode());
        }
        String aAPIResult = EntityUtils.toString(response.getEntity());
        return MockTranslator.fromCreateResult(aAPIResult);
    }

    public static class MockTranslator {

        public static String toCreateRequest(String mockId) {
            return "{mockId:\" "+ mockId +"\"}";
        }

        public static MockResult fromCreateResult(String result) {
            return null;
        }
    }

    public static class MockResult {

    }

}
