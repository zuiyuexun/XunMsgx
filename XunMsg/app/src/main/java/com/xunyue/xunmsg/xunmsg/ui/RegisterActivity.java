package com.xunyue.xunmsg.xunmsg.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xunyue.xunmsg.xunmsg.Dialog.DialogFragmentManager;
import com.xunyue.xunmsg.xunmsg.Dialog.LoadDialogFragment;
import com.xunyue.xunmsg.xunmsg.R;
import com.xunyue.xunmsg.xunmsg.util.Base64Util;
import com.xunyue.xunmsg.xunmsg.util.ToastUtil;
import com.xunyue.xunmsg.xunmsg.util.XunMsgTextWatcher;
import com.xunyue.xunmsg.xunmsg.util.Constant;
import com.xunyue.xunmsg.xunmsg.util.ErrorCodeUtil;

import java.io.UnsupportedEncodingException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends TitleBarActivity implements View.OnClickListener, XunMsgTextWatcher.OnTextChangedListener {
    private static final String TAG="Register";

    private EditText mUserName;
    private EditText mPwd;
    private EditText mConfirmPwd;
    private EditText mEmail;
    private Button mRegister;

    private TextInputLayout mTilUsername;
    private TextInputLayout mTilPwd;
    private TextInputLayout mTilConfirmPwd;
    private TextInputLayout mTilEmail;


    private LoadDialogFragment loadDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTitleBar();
        initView();
        initData();
    }


    private void initTitleBar() {
        setTitleName(R.string.register);
    }

    public void initView() {
        mUserName = (EditText) findViewById(R.id.et_user_name);
        mPwd = (EditText) findViewById(R.id.et_pwd);
        mConfirmPwd = (EditText) findViewById(R.id.et_confirm_pwd);
        mEmail = (EditText) findViewById(R.id.et_email);
        mRegister = (Button) findViewById(R.id.bt_register);
        mTilUsername = (TextInputLayout) findViewById(R.id.til_user_name);
        mTilPwd = (TextInputLayout) findViewById(R.id.til_pwd);
        mTilConfirmPwd = (TextInputLayout) findViewById(R.id.til_confirm_pwd);
        mTilEmail = (TextInputLayout) findViewById(R.id.til_eamil);

        mUserName.addTextChangedListener(new XunMsgTextWatcher(this, Constant.USER_NAME));
        mPwd.addTextChangedListener(new XunMsgTextWatcher(this,Constant.PWD));
        mConfirmPwd.addTextChangedListener(new XunMsgTextWatcher(this,Constant.CONFIRM_PWD));
        mEmail.addTextChangedListener(new XunMsgTextWatcher(this,Constant.EMAIL));

        mRegister.setOnClickListener(this);
    }
    private void initData() {

    }


    @Override
    public void onClick(View view) {
        final String userName = mUserName.getText().toString();
        String email = mEmail.getText().toString();
        String pwd= mPwd.getText().toString();
        String ConfirmPwd = mConfirmPwd.getText().toString();

        if (userName.isEmpty()) {
            mTilUsername.setError("用户名不可为空");
            return;
        }else if (email.isEmpty()) {
            mTilEmail.setError("邮箱不可为空");
            return ;
        }else if (pwd.isEmpty()) {
            mTilPwd.setError("密码不可为空");
            return;
        }else if (ConfirmPwd.isEmpty()) {
            mTilConfirmPwd.setError("确认密码不可为空");
            return;
        }else if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
            mTilEmail.setError("邮箱格式不正确");
            return;
        }else if (!pwd.equals(ConfirmPwd)) {
            ToastUtil.toast(this,"密码不一致");
            return;
        }else if (userName.length()>mTilUsername.getCounterMaxLength()) {
            return;
        }
        loadDialogFragment= DialogFragmentManager.showLoadDialogFragment(getFragmentManager(),TAG);
        BmobUser user = new BmobUser();
        try {
            user.setUsername(userName);
            pwd=Base64Util.encryption(pwd);
            user.setPassword(pwd);
            user.setEmail(email);
            user.setEmailVerified(false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                loadDialogFragment.dismissAllowingStateLoss();
                if (e==null) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.USER_NAME,userName);
                    setResult(Constant.RESULT_OK,intent);
                    finish();
                    ToastUtil.toast(RegisterActivity.this,"注册成功,请验证邮箱");
                }else {
                    String msg= ErrorCodeUtil.errorCode(e.getErrorCode());
                    if (msg==null) msg=e.getMessage();
                    ToastUtil.toast(RegisterActivity.this,msg);
                }

            }
        });

    }


    @Override
    public void onNewTextChanged(CharSequence charSequence, String tag) {
        String text = charSequence.toString();
        switch (tag) {
            case Constant.USER_NAME :
                if (text.length()>mTilUsername.getCounterMaxLength()) {
                    mTilUsername.setError("字数不超过"+mTilUsername.getCounterMaxLength()+"位");
                }else {
                    mTilUsername.setError("");
                }
                break;
            case Constant.PWD :
                if (!text.isEmpty()) {
                    mTilPwd.setError("");
                }
                break;
            case Constant.CONFIRM_PWD :
                if (!text.isEmpty()) {
                    mTilConfirmPwd.setError("");
                }
                break;
            case Constant.EMAIL :
                if (!text.isEmpty()) {
                    mTilEmail.setError("");
                }
                break;
        }
    }
}
