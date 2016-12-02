package com.xunyue.xunmsg.xunmsg.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xunyue.xunmsg.xunmsg.R;
import com.xunyue.xunmsg.xunmsg.ui.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }


}
