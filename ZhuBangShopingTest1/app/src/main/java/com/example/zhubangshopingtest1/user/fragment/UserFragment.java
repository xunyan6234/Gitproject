package com.example.zhubangshopingtest1.home.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.base.BaseFragment;
import com.example.zhubangshopingtest1.user.man.UserManActivtiy;
import com.example.zhubangshopingtest1.user.man.UserLoginActivity;

public class UserFragment extends BaseFragment {

    private ImageView wode_touxiang;
    private static final String TAG =
            UserFragment.class.getSimpleName();
    private TextView textView;
    private View view;
    private Button nojoin;

    @Override
    public View initView() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("data", 0);
        int join_key = preferences.getInt("join_key", 0);
        Log.e("TAG", "initView: 获得数据+" + join_key);

        if (join_key == 1) {
            //已经登录，提取信息
            view = View.inflate(mContext, R.layout.fragment_user, null);
            Log.e(TAG, "initView: 已登录，显示个人中心");
            wode_touxiang = (ImageView) view.findViewById(R.id.wode_touxiang);
            UserListener();
        } else {
            //说明没有登录，提示登录
            view = View.inflate(mContext, R.layout.user_nojoin, null);
            Button nojoin = (Button) view.findViewById(R.id.nojoin);
            Log.e(TAG, "initView: 显示未登录界面");
            nojoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UserLoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        Log.e(TAG, "个人中心ui被初始化了");
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "个人中心数据被初始化了");

//        textView.setText("个人中心");
    }

    private void UserListener() {
        wode_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserManActivtiy.class);
                startActivity(intent);
                Log.e(TAG, "onClick:点击头像跳转到个人信息 ");
            }
        });
    }


}