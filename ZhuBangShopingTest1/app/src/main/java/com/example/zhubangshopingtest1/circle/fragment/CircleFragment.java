package com.example.zhubangshopingtest1.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.base.BaseFragment;

public class CircleFragment extends BaseFragment {
    private static final String TAG =
            CircleFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"圈子ui被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_circle, null);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"圈子数据被初始化了");
//        textView.setText("圈子");
    } }