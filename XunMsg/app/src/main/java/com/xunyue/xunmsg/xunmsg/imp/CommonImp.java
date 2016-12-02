package com.xunyue.xunmsg.xunmsg.imp;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface CommonImp {

    void onSuccess(String tag,Object object);

    void onFail(String tag,String errorMsg);
}
