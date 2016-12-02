package com.xunyue.xunmsg.xunmsg.Dialog;

import android.app.FragmentManager;

/**
 * Created by Administrator on 2016/12/2.
 */
public class DialogFragmentManager {

    public static LoadDialogFragment showLoadDialogFragment(FragmentManager manager, String tag) {
        LoadDialogFragment loadDialogFragment = LoadDialogFragment.newInstense();
        loadDialogFragment.show(manager,tag);
        return loadDialogFragment;
    }
}
