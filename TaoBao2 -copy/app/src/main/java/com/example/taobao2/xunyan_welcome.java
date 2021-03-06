package com.example.taobao2;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;


/**
 * 欢迎界面
 */
public class xunyan_welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xunyan_welcome);
        //两秒钟进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动MainActivity主页面，这段代码是在主线程执行
                startActivity(new Intent(xunyan_welcome.this,MainActivity.class));
                //关闭当前页面（结束WelcomeActivity）
                finish();
            }
        },2000);
    }
}