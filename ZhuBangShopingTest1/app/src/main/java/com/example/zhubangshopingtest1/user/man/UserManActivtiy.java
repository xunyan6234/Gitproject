package com.example.zhubangshopingtest1.user.man;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.zhubangshopingtest1.MainActivity;
import com.example.zhubangshopingtest1.R;
public class UserManActivtiy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_man_activtiy);



    }

    public void onClick(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                //启动主页面
                SharedPreferences.Editor editor = getSharedPreferences("data", 0).edit();
                //MODE_PRIVATE 也可以用 0
                editor.putInt("join_key", 0);
                editor.apply();
            }
        },2000);

        SharedPreferences i = getSharedPreferences("data", 0);
        Log.e("TAG", "join_key" + i.getInt("join_key", 0) );
        //关闭当前页面
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //退出成功，重新刷新主页

    }
}