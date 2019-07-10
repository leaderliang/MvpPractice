package com.android.mvp.presenter;

import com.android.mvp.bean.BaseObjectBean;
import com.android.mvp.bean.LoginBean;
import com.android.mvp.contract.MainContract;
import com.android.mvp.mvp.mvp.BaseModel;
import com.android.mvp.mvp.mvp.BaseMvpPresenter;
import com.android.mvp.net.RxScheduler;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * TODO MainPresenter
 * 请求示例
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public class MainPresenter extends BaseMvpPresenter<MainContract.View> implements MainContract.Presenter {

    private BaseModel mModel;
//    private MainContract.Model model;

    public MainPresenter() {
//        model = new MainModel();
        mModel = new BaseModel();
    }

    @Override
    public void login(String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求  accept 里也需要做相应判断
        if (!isViewAttached()) {
            return;
        }
        mMvpView.showLoading();
//        ArrayCompositeSubscription CompositeSubscription;
        mModel.login(username, password)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main())
                .as(mMvpView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Subscriber<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);  //注意这句代码
                    }

                    @Override
                    public void onNext(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        mMvpView.onSuccess(loginBeanBaseObjectBean);
                        mMvpView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        mMvpView.onThrowable(t);
                        mMvpView.dismissLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
