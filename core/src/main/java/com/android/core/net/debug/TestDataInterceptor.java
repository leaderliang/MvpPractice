package com.android.core.net.debug;


import com.android.core.application.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author hesen
 * @description 网络请求测试数据拦截器
 * @since 2019/7/11 11:58
 */
public class TestDataInterceptor implements Interceptor {

    //接口测试数据
    private Map<String, String> mJsonDatas = new HashMap<>();

    public TestDataInterceptor() {
        parseData();
    }

    private void parseData() {
        try {
            InputStream inputStream = MyApplication.getInstance().getAssets().open("data.json");
            String json = StringUtils.convertStreamToString(inputStream);
            String[] split = json.split("##");
            for (int i = 1; i < split.length; i += 2) {
                mJsonDatas.put(split[i], split[i + 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        URL url = chain.request().url().url();
        String urlPath = url.getPath();
        String message = getMessage(urlPath);
        return new Response.Builder()
                .code(200)
                .message(message)
                .body(ResponseBody.create(MediaType.parse("application/data"), message.getBytes()))
                .protocol(Protocol.HTTP_1_0)
                .request(chain.request())
                .addHeader("content-type", "application/json")
                .addHeader("charset", "UTF-8")
                .build();
    }

    private String getMessage(String urlPath) {
        if (mJsonDatas.containsKey(urlPath)) {
            return mJsonDatas.get(urlPath).replaceAll("/n", "");
        } else {
            return "";
        }
    }
}
