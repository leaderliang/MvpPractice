package com.android.core.contract;


import com.android.core.bean.BaseResponse;
import com.android.core.bean.LoginBean;
import com.android.core.mvp.mvp.MvpPresenter;
import com.android.core.mvp.mvp.MvpView;

/**
 * 是一个契约，将Model、View、Presenter 进行约束管理，方便后期类的查找、维护
 *
 * @author dev.liang
 */
public interface MainContract {

    interface View extends MvpView {
        @Override
        void showLoading();

        @Override
        void dismissLoading();

        @Override
        void onError(String error);

        void onSuccess(BaseResponse<LoginBean> bean);
    }

    interface Presenter extends MvpPresenter<View> {
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
