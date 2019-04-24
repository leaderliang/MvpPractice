package com.android.mvp.presenter;

import com.android.mvp.bean.Repo;
import com.android.mvp.contract.UserReposContract;
import com.android.mvp.model.UserRepoModel;
import com.android.mvp.mvp.mvp.BaseMvpPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 18:32
 */
public class UserReposPresenter extends BaseMvpPresenter<UserReposContract.View> implements UserReposContract.Presenter {

    private final UserReposContract.Model model;

    public UserReposPresenter() {
        model = new UserRepoModel();
    }

    @Override
    public void getUserRepo() {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mMvpView.showLoading();
        model.getUserRepo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repos) throws Exception {
                        mMvpView.onSuccess(repos);
                        mMvpView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mMvpView.onError(throwable);
                        mMvpView.hideLoading();
                    }
                });

    }
}
