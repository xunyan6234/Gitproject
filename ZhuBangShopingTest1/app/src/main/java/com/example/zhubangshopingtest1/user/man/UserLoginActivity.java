package com.example.zhubangshopingtest1.user.man;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhubangshopingtest1.MainActivity;
import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.app.Welcome;
import com.example.zhubangshopingtest1.home.fragment.UserFragment;

import java.util.Map;

public class UserLoginActivity extends Activity implements View.OnClickListener {

    private EditText account;
    private EditText password;
    private Button btnLogin;
    private Button btnSign;
    private String act,psw;

    private static final String TAG =
            UserLoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_join);
        findViews();

    }

    private void findViews() {
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSign = (Button) findViewById(R.id.btn_sign);

        btnLogin.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
//                act = account.getText().toString();
//                psw = password.getText().toString();
                act = "123";
                psw = "123";
                Log.e("TAG", "打印账号" + act + "密码" + psw);
                if (act == "123" && psw == "123") {
//                    账号密码正确
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences.Editor editor = getSharedPreferences("data", 0).edit();
                            editor.putInt("join_key", 1);
                            editor.apply();
                        }
                    },2000);
                    //关闭当前页面
                    finish();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    //登录成功，重新刷新主页
                } else {
                    //密码错误
                    Log.e(TAG, "onClick: 密码错误，重新登录");
                }
                break;
            case R.id.btn_sign:
                break;
        }
    }
}