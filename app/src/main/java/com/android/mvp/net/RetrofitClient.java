package com.android.mvp.net;


import android.support.annotation.NonNull;


import com.android.mvp.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO RetrofitClient
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;

    private String baseUrl = "https://api.github.com";

    private ApiService apiService;

    private Retrofit retrofit;

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        //添加Token
                        .header("token", "");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     *
     * @return
     */
    private Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    public ApiService getApi() {
        if (apiService == null) {
            // 初始化一个client,不然retrofit会自己默认添加一个
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(9, TimeUnit.SECONDS);
            //设置Header
            //builder.addInterceptor(getHeaderInterceptor());
            builder.retryOnConnectionFailure(true);

        /*OkHttpClient.Builder builders = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .build();*/

            if (BuildConfig.DEBUG) {
                /*打印日志拦截器*/
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置拦截器
                builder.addInterceptor(interceptor);
            }
            retrofit = new Retrofit.Builder()
                    //设置网络请求的Url地址
                    .baseUrl(baseUrl)
                    .client(builder.build())
                    //设置数据解析器
                    .addConverterFactory(GsonConverterFactory.create())
                    //设置网络请求适配器，使其支持RxJava与RxAndroid
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            //创建—— 网络请求接口—— 实例
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


}
