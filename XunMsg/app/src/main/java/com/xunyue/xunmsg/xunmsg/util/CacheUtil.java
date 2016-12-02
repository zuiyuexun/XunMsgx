package com.xunyue.xunmsg.xunmsg.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CacheUtil {

    public static void save(Context context,String fileName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
    }

    public static String quary(Context context,String fileName,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,context.MODE_APPEND);
        return sharedPreferences.getString(key,null);
    }

    public static void saveUserInfo(Context context,BmobUser bmobUser) throws IOException {
        String path=context.getCacheDir().getAbsolutePath()+"user.txt";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(bmobUser);
        out.flush();
        out.close();
    }

    public static BmobUser getUserInfo(Context context) throws IOException, ClassNotFoundException {
        String path=context.getCacheDir().getAbsolutePath()+"user.txt";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        BmobUser bmobUser= (BmobUser) in.readObject();
        in.close();
        return bmobUser;
    }


}
