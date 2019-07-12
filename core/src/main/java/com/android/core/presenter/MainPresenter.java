package com.android.core.presenter;

import com.android.core.bean.BaseResponse;
import com.android.core.bean.LoginBean;
import com.android.core.contract.MainContract;
import com.android.core.mvp.mvp.BaseModel;
import com.android.core.mvp.mvp.BaseMvpPresenter;
import com.android.core.net.BaseFlowable;
import com.android.core.net.RxScheduler;


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
                .compose(RxScheduler.<BaseResponse<LoginBean>>Flo_io_main())
                .as(mMvpView.<BaseResponse<LoginBean>>bindAutoDispose())
                .subscribe(new BaseFlowable<LoginBean>() {
                    @Override
                    protected void onSuccess(BaseResponse<LoginBean> response) {
                        mMvpView.onSuccess(response);
                        mMvpView.dismissLoading();
                    }

                    @Override
                    protected void onFailure(BaseResponse<LoginBean> errorResponse) {
                        mMvpView.dismissLoading();
                    }

                    @Override
                    protected void onNetError(Throwable throwable) {
//                        super.onNetError(throwable);
                        mMvpView.onThrowable(throwable);
                    }
                });
    }
}
