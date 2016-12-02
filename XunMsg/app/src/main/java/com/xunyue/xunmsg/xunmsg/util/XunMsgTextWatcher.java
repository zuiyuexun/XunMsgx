package com.xunyue.xunmsg.xunmsg.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2016/12/2.
 */
public class XunMsgTextWatcher implements TextWatcher{
    private OnTextChangedListener listener;
    private String tag;
    public XunMsgTextWatcher(OnTextChangedListener listener,String tag) {
        this.listener = listener;
        this.tag = tag;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        listener.onNewTextChanged(charSequence,tag);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public interface OnTextChangedListener {
        void onNewTextChanged(CharSequence charSequence,String tag);
    }

}
