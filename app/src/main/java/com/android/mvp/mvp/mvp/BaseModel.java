package com.android.mvp.mvp.mvp;

import com.android.mvp.bean.BaseResponse;
import com.android.mvp.bean.LoginBean;
import com.android.mvp.bean.Repo;
import com.android.mvp.net.RetrofitClient;
import com.android.mvp.net.RetrofitService;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * BaseModel
 * @author devliang
 */
public class BaseModel implements BaseMvpModel {
    private final String TAG = BaseModel.class.getSimpleName();

    private RetrofitService mRetrofitService;

    public BaseModel() {
        this.mRetrofitService = RetrofitClient.getInstance().getApiService();
    }

    public Flowable<BaseResponse<LoginBean>> login(String username, String password) {
        return mRetrofitService.login(username, password);
    }


    public Observable<BaseResponse<List<Repo>>> getUserRepo() {
        return RetrofitClient.getInstance().getApiService().getUserRepos();
    }

    // ---- 更多相应接口 -----

}
