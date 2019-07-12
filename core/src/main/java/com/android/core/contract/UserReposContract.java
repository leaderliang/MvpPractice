package com.android.core.contract;


import com.android.core.bean.Repo;
import com.android.core.mvp.mvp.MvpPresenter;
import com.android.core.mvp.mvp.MvpView;

import java.util.List;

/**
 * 是一个契约，将Model、View、Presenter 进行约束管理，方便后期类的查找、维护
 *
 * @author dev.liang
 */
public interface UserReposContract {

    interface View extends MvpView {
        @Override
        void showLoading();

        @Override
        void dismissLoading();

        @Override
        void onError(String error);

        void onSuccess(List<Repo> bean);
    }

    interface Presenter extends MvpPresenter<View> {

        void getUserRepo();
    }
}
