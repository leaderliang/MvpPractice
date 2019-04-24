package com.android.mvp.model;


import com.android.mvp.bean.Repo;
import com.android.mvp.contract.UserReposContract;
import com.android.mvp.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

/**
 * TODO MainModel
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public class UserRepoModel implements UserReposContract.Model {

    @Override
    public Observable<List<Repo>> getUserRepo() {
        return RetrofitClient.getInstance().getApi().getUserRepos();
    }
}
