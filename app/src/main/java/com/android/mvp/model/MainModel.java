package com.android.mvp.model;


import com.android.mvp.bean.BaseObjectBean;
import com.android.mvp.bean.LoginBean;
import com.android.mvp.contract.MainContract;
import com.android.mvp.net.RetrofitClient;

import io.reactivex.Flowable;

/**
 * TODO MainModel
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public class MainModel implements MainContract.Model {
    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(String username, String password) {
        return RetrofitClient.getInstance().getApi().login(username,password);
    }
}
