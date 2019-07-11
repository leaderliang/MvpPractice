package com.android.mvp.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/28 01:20
 */
public class AppConstant {

    /*用于对响应结果做出类型化描述(如「获取成功」「内容未找到」)*/
    /**
     * 1xx:临时性消息。如:100 (继续发送)、101(正在切换协议)
     */
    public static final int HTTP_RESPONSE_100 = 100;
    /**
     * 2xx:成功。最典型的是 200(OK)、201(创建成功)
     */
    public static final int HTTP_RESPONSE_200 = 200;
    /**
     * 3xx:重定向。如 301(永久移动)、302(暂时移动)、304(内容未改变)
     */
    public static final int HTTP_RESPONSE_301 = 301;
    /**
     * 4xx:客户端错误。如 400(客户端请求错误)、401(认证失败)、403(被禁⽌止)、404(找 不不到内容)
     */
    public static final int HTTP_RESPONSE_400 = 400;
    /**
     * 5xx:服务器器错误。如 500(服务器器内部错误)
     */
    public static final int HTTP_RESPONSE_500 = 500;


    /**
     * 测试图片数据
     *
     * @return
     */
    public static List<String> getImageUrl() {
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.bai.com/timg?image&quality=80&size=b9999_10000&sec=1556781467378&di=b79b56ea99be0dee0d57693ee3fb3f02&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201303%2F16%2F20130316114138_2CSdZ.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467378&di=b79b56ea99be0dee0d57693ee3fb3f02&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201303%2F16%2F20130316114138_2CSdZ.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556783439128&di=8e4053f79122b047229f4087870afd45&imgtype=0&src=http%3A%2F%2Fstatic-xiaoguotu.17house.com%2Fxgt%2Fs%2F21%2F1462885931154aa.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467378&di=e644c7bc3bcefffd42c4a90f5b06d692&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201605%2F07%2F20160507140223_nYwc3.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467378&di=b79b56ea99be0dee0d57693ee3fb3f02&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201303%2F16%2F20130316114138_2CSdZ.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467378&di=9ba5b9ec3c4cd9e3c38c9204e5838428&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201801%2F21%2F20180121203444_FRzZy.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467377&di=2924dc884f07b3b1d28aee81b639df7b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F07%2F20180607083016_VjzR3.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467377&di=2924dc884f07b3b1d28aee81b639df7b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F07%2F20180607083016_VjzR3.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467376&di=904ffc4dd73d9b9f348ed1b0e924d990&imgtype=0&src=http%3A%2F%2Fimg.idol001.com%2Forigin%2F2018%2F02%2F09%2Fb6876a904b81707ddfa39dcf23069a091518158812.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556781467376&di=20526c6eb3f09fda242b6fd82fb17918&imgtype=0&src=http%3A%2F%2Fimage3.xyzs.com%2Fupload%2F9d%2Ffc%2F311%2F20150506%2F143087191032494_0.jpg");
        return list;
    }

}
