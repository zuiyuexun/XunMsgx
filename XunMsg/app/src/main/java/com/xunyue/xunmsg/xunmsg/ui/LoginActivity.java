package com.xunyue.xunmsg.xunmsg.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

import com.xunyue.xunmsg.xunmsg.Dialog.DialogFragmentManager;
import com.xunyue.xunmsg.xunmsg.Dialog.LoadDialogFragment;
import com.xunyue.xunmsg.xunmsg.R;
import com.xunyue.xunmsg.xunmsg.util.ToastUtil;
import com.xunyue.xunmsg.xunmsg.util.XunMsgTextWatcher;
import com.xunyue.xunmsg.xunmsg.util.CacheUtil;
import com.xunyue.xunmsg.xunmsg.util.Constant;
import com.xunyue.xunmsg.xunmsg.util.ErrorCodeUtil;

import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, XunMsgTextWatcher.OnTextChangedListener {
    private static final String TAG = "login";

    private EditText mUserName;
    private EditText mPwd;
    private TextInputLayout mTilUserName;
    private TextInputLayout mTilPwd;

    private Button mLogin;
    private Button register;
    private LoadDialogFragment loadDialogFragment;

    private Checkable mRememberPwd;
    private TextView mForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }



    private void initView() {
        mUserName = (EditText) findViewById(R.id.et_user_name);
        mPwd = (EditText) findViewById(R.id.et_pwd);
        mTilUserName = (TextInputLayout) findViewById(R.id.til_user_name);
        mTilPwd = (TextInputLayout) findViewById(R.id.til_pwd);
        mRememberPwd = (Checkable) findViewById(R.id.cb_remember_password);
        mForgetPwd = (TextView) findViewById(R.id.tv_forget_password);

        mUserName.addTextChangedListener(new XunMsgTextWatcher(this, Constant.USER_NAME));
        mPwd.addTextChangedListener(new XunMsgTextWatcher(this,Constant.PWD));

        register = (Button) findViewById(R.id.tv_register);
        mLogin = (Button) findViewById(R.id.tv_login);

        mForgetPwd.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        register.setOnClickListener(this);
    }
    private void initData() {
        try {
            BmobUser user=CacheUtil.getUserInfo(this);
            if (user!=null) {
             user.login(new SaveListener<BmobUser>() {
                 @Override
                 public void done(BmobUser bmobUser, BmobException e) {
                     if (e==null) {

                     }else {

                     }
                 }
             });
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register :
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivityForResult(intent,200);
                break;
            case R.id.tv_login :
                login();
                break;
            case R.id.tv_forget_password :

                break;
        }
    }

    private void login() {

        final String userName = mUserName.getText().toString();
        final String pwd = mPwd.getText().toString();
        if (userName.isEmpty()) {
            mTilUserName.setError("用户名不可为空");
            return;
        }else if (pwd.isEmpty()) {
            mTilPwd.setError("密码不可为空");
            return;
        }
        loadDialogFragment= DialogFragmentManager.showLoadDialogFragment(getFragmentManager(),TAG);
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(userName);
        bu2.setPassword(pwd);
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                loadDialogFragment.dismissAllowingStateLoss();

                if(e==null){
                    if (!bmobUser.getEmailVerified()) {
                        ToastUtil.toast(LoginActivity.this,"请验证邮箱");
                        return;
                    }
                    ToastUtil.toast(LoginActivity.this,"登录成功");
                    if (mRememberPwd.isChecked()){
                        try {
                            CacheUtil.saveUserInfo(LoginActivity.this,bmobUser);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    String msg= ErrorCodeUtil.errorCode(e.getErrorCode());
                    if (msg==null) msg=e.getMessage();
                   ToastUtil.toast(LoginActivity.this,msg);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==Constant.RESULT_OK) {
            mUserName.setText(data.getStringExtra(Constant.USER_NAME));
        }
    }

    @Override
    public void onNewTextChanged(CharSequence charSequence, String tag) {
        if(mUserName.getText().toString().length() > 0 && mPwd.getText().toString().length() > 0) {
            mLogin.setEnabled(true);
        }else {
            mLogin.setEnabled(false);
        }
        String text = charSequence.toString();
        switch (tag) {
            case Constant.USER_NAME :
                if (!text.isEmpty()) {
                    mTilUserName.setError("");
                }
                break;
            case Constant.PWD :
                if (!text.isEmpty()) {
                    mTilPwd.setError("");
                }
                break;
        }
    }
}
