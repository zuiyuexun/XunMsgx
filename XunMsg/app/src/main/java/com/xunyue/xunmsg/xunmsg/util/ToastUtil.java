package com.xunyue.xunmsg.xunmsg.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/2.
 */
public class ToastUtil {

    public static void toast(Context context,String content) {
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }


    public static void toast(Context context,int resId) {
        Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
    }

}
