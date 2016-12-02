package com.xunyue.xunmsg.xunmsg.Dialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunyue.xunmsg.xunmsg.R;

/**
 * Created by Administrator on 2016/12/2.
 */
public class LoadDialogFragment extends BaseDialogFragment {

    private LoadDialogFragment() {

    }

    public static LoadDialogFragment newInstense() {
        return new LoadDialogFragment();
    }

    @Override
    public View onCreateDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.dialog_load,null);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this,tag);
        ft.commitAllowingStateLoss();
    }
}
