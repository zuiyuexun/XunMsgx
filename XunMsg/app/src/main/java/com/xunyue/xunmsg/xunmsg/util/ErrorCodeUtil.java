package com.xunyue.xunmsg.xunmsg.util;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/2.
 */
public class ErrorCodeUtil {


    public static String  errorCode(int code) {
        String errorName=null;
        switch (code) {
            case 101 :
                errorName ="登录接口的用户名或密码不正确";
                break;
            case 108 :
                errorName ="用户名和密码是必需的";
                break;
            case 202 :
                errorName="用户名已经存在";
                break;
            case 203 :
                errorName="邮箱已经存在";
                break;
            case 205 :
                errorName="没有找到此邮件的用户";
                break;

        }
        return errorName;
    }



}
