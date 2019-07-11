package com.android.mvp.net.network;

import com.android.mvp.net.network.entities.LoginRequestEntity;

/**
 * @author hesen
 * @description
 * @since 2019/7/10 15:15
 */
public class ObservableManager {

    private static class SingletonHolder {
        static final ObservableManager INSTANCE = new ObservableManager();
    }

    public static ObservableManager getInstance() {
        return ObservableManager.SingletonHolder.INSTANCE;
    }

    /**
     * 获取loginRequestEntity请求实体
     */
    public BaseRequestEntity<LoginRequestEntity> getLoginRequestEntity() {
        BaseRequestEntity<LoginRequestEntity> requestModel = new BaseRequestEntity<>();
        requestModel.setHeader(HeaderUtils.getHeaderModel());
        LoginRequestEntity loginRequestModel = new LoginRequestEntity();
        requestModel.setData(loginRequestModel);
        return requestModel;
    }

}

