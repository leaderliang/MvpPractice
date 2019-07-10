package com.android.mvp.presenter;

import com.android.mvp.bean.Repo;
import com.android.mvp.contract.UserReposContract;
import com.android.mvp.mvp.mvp.BaseModel;
import com.android.mvp.mvp.mvp.BaseMvpPresenter;
import com.android.mvp.net.RxScheduler;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 18:32
 */
public class UserReposPresenter extends BaseMvpPresenter<UserReposContract.View> implements UserReposContract.Presenter {
    private final BaseModel mModel;

    public UserReposPresenter() {
        mModel = new BaseModel();
    }

    @Override
    public void getUserRepo() {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mMvpView.showLoading();
        mModel.getUserRepo()
                .compose(RxScheduler.Obs_io_main())
                .as(mMvpView.bindAutoDispose())
                .subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repos) throws Exception {
                        mMvpView.onSuccess(repos);
                        mMvpView.dismissLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mMvpView.onThrowable(throwable);
                        mMvpView.dismissLoading();
                    }
                });

    }
}
