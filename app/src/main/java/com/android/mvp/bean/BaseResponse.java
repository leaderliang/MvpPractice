package com.android.mvp.bean;

import android.content.Context;

import com.android.mvp.constant.AppConstant;
import com.android.mvp.net.NetworkError;
import com.android.mvp.net.ServerException;

/**
 * {@link BaseResponse}
 * 三位数字，⽤用于对响应结果做出类型化描述(如「获取成功」「内容未找到」)。
 * 1xx:临时性消息。如:100 (继续发送)、101(正在切换协议)
 * 2xx:成功。最典型的是 200(OK)、201(创建成功)。
 * 3xx:重定向。如 301(永久移动)、302(暂时移动)、304(内容未改变)。 4xx:客户端错误。如 400(客户端请求错误)、401(认证失败)、403(被禁⽌止)、404(找 不不到内容)。
 * 5xx:服务器器错误。如 500(服务器器内部错误)。
 *
 * @author devliang
 * @date 2019-07-11 14:14:06
 */
public class BaseResponse<T> {

    private int statusCode;

    private String resultMessage;

    private T resultData;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public boolean resultOk() {
        // 接口成功
        if (statusCode == AppConstant.HTTP_RESPONSE_200) {
            return true;
        } else {
            NetworkError.error(new ServerException(statusCode, resultMessage));
            return false;
        }
    }


}
