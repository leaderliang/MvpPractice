package com.android.mvp.presenter;

import com.android.mvp.bean.BaseObjectBean;
import com.android.mvp.bean.LoginBean;
import com.android.mvp.contract.MainContract;
import com.android.mvp.model.MainModel;
import com.android.mvp.mvp.mvp.BaseMvpPresenter;
import com.android.mvp.net.RxScheduler;

import io.reactivex.functions.Consumer;

/**
 * TODO MainPresenter
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public class MainPresenter extends BaseMvpPresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;

    public MainPresenter() {
        model = new MainModel();
    }

    @Override
    public void login(String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mMvpView.showLoading();
        model.login(username, password)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main())
                .as(mMvpView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void accept(BaseObjectBean<LoginBean> bean) throws Exception {
                        mMvpView.onSuccess(bean);
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
