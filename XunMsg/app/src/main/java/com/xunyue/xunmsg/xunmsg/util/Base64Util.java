package com.xunyue.xunmsg.xunmsg.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/12/2.
 */
public class Base64Util {

    /**
     * 加密
     * @param pwd
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryption(String pwd) throws UnsupportedEncodingException {
        byte[] bytes = pwd.getBytes("utf-8");
        for (int i=0;i<bytes.length;i++) {
            if (i%2==0) {
                bytes[i] +=1;
            }else {
                bytes[i] -= 1;
            }
        }
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    /**
     * 解密
     * @param pwd
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Decrypt(String pwd) throws UnsupportedEncodingException {
        byte[] b = pwd.getBytes("utf-8");
        for(int i=0;i<b.length;i++) {
            if (i%2==0) {
                b[i] -=1;
            }else {
                b[i] +=1;
            }
        }
       return new String( Base64.decode(b,Base64.DEFAULT), Charset.defaultCharset());
    }
}
