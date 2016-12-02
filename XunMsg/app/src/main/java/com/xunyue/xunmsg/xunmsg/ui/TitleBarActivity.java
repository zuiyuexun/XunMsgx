package com.xunyue.xunmsg.xunmsg.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunyue.xunmsg.xunmsg.R;

/**
 * Created by Administrator on 2016/12/1.
 */
public class TitleBarActivity extends AppCompatActivity  {
    private ViewGroup view;
    private TextView title;
    private TextView back;
    private TextView right;
    private OnClickRightButtonListener onClickRightButtonListener;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, view);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         view = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_titlebar,null);
        setContentView(view);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.tv_title);
        back = (TextView) findViewById(R.id.tv_back);
        right = (TextView) findViewById(R.id.tv_right);

        InnerClass innerClass = new InnerClass();
        back.setOnClickListener(innerClass);
        right.setOnClickListener(innerClass);
    }

    private class InnerClass implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_back :
                    finish();
                    break;
                case R.id.tv_right :
                    onClickRightButtonListener.onClickRightButton(view);
                    break;
            }
        }
    }


    public void setTitleName(int StringId) {
        title.setText(StringId);
    }

    public void setOnClickRightButton(OnClickRightButtonListener onClickRightButtonListener) {
        this.onClickRightButtonListener = onClickRightButtonListener;
    }

    public interface OnClickRightButtonListener {
        void onClickRightButton(View view);
    }
}
