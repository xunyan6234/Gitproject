package com.example.zhubangshopingtest1.app;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 定义一个Application
 */

public class MyApplication extends Application{

    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /**
         * 初始化OkHttpUtils
         */
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS) //链接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS) //读取超时
                .build(); //其他配置
        Log.e("xunyan", "okhttp配置完成 initOkHttpClient: ");
        OkHttpUtils.initClient(okHttpClient);
    }
}