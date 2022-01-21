package me.liberty.ddd.biz.service.account.impl;

import me.liberty.ddd.common.adapter.impl.MockSystemAdapterImpl;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author yuanshouna@gmail.com
 * @created 2022-01-17 16:40
 */
public class AnticorrosDemoApplicationService {

    private HttpClient httpClient;

    private BSystemFacade bSystemFacade;

    private MockSystemAdapterImpl mockSystemAdapter;


    public void testA() throws IOException {

        // ... 请求 A 系统的接口
        HttpPost httpPost = new HttpPost("https://xxx");
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("请求下游系统失败" + response.getStatusLine().getStatusCode());
        }
        String aAPIResult = EntityUtils.toString(response.getEntity());

        //... 请求 B 系统的接口
        bSystemFacade.exec();

        //... 继续执行其他逻辑

    }

    public void testB() throws IOException {
        // 请求 A 系统的接口
        mockSystemAdapter.create("");

        // ... 请求 B，执行其他逻辑
    }

    public static class BSystemFacade {

        public void exec(){

        }
    }
}
