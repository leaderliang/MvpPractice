package com.android.mvp.ui.activity;

import android.os.Bundle;

import com.android.mvp.R;
import com.android.mvp.bean.Repo;
import com.android.mvp.contract.UserReposContract;
import com.android.mvp.mvp.activity.BaseActivity;
import com.android.mvp.presenter.UserReposPresenter;
import com.android.mvp.utils.JsonFormat;
import com.android.mvp.utils.Trace;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 12:25
 */
public class MainActivity extends BaseActivity<UserReposContract.View, UserReposContract.Presenter> implements UserReposContract.View {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    public UserReposContract.Presenter createMvpPresenter() {
        return new UserReposPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        getMvpPresenter().getUserRepo();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onSuccess(List<Repo> bean) {
        Trace.e(TAG, JsonFormat.format(new Gson().toJson(bean)));
//        Log.e(TAG,"----->"+new Gson().toJson(bean));
    }
}
