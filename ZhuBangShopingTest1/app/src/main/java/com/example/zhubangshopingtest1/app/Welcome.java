package com.example.zhubangshopingtest1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.zhubangshopingtest1.MainActivity;
import com.example.zhubangshopingtest1.R;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                //启动主页面
                startActivity(new Intent(Welcome.this, MainActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);
    }
}