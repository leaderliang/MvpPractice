package com.android.mvp.net;


import com.android.mvp.bean.BaseResponse;
import com.android.mvp.bean.LoginBean;
import com.android.mvp.bean.Repo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * TODO ApiService
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public interface RetrofitService {

    /**
     * 登陆
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<BaseResponse<LoginBean>> login(@Field("username") String username,
                                              @Field("password") String password);


    @GET("/users/leaderliang/repos")
    Call<List<Repo>> getUserRepos_();


    /**
     * @return
     */
    @GET("/users/leaderliang/repos")
    Observable<BaseResponse<List<Repo>>> getUserRepos();

}
