package com.xunyue.xunmsg.xunmsg.Dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xunyue.xunmsg.xunmsg.R;

/**
 * Created by Administrator on 2016/12/2.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        final Window window = getDialog().getWindow();
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        window.setBackgroundDrawableResource(R.drawable.dialog_color);//注意此处
        float scale = getActivity().getResources().getDisplayMetrics().density;
        window.setLayout((int) (300*scale+0.5f),window.getAttributes().height);//这2行,和上面的一样,注意顺序就行;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return onCreateDialogView(inflater,container,savedInstanceState);
    }


     public abstract  View onCreateDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


}
