package com.example.zhubangshopingtest1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.zhubangshopingtest1.base.BaseFragment;

import com.example.zhubangshopingtest1.home.fragment.CircleFragment;
import com.example.zhubangshopingtest1.home.fragment.HomeFragment;

import com.example.zhubangshopingtest1.home.fragment.ShoppingFragment;
import com.example.zhubangshopingtest1.home.fragment.UserFragment;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragemnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        HideTitle();        //隐藏标题；
        initFragment();     //初始化Fragment
        initListener();     //监听切换
    }

    private void HideTitle() {
        ActionBar actionbarr = getSupportActionBar();
        if (actionbarr != null) {
            actionbarr.hide();
        }       //隐藏标题；

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CircleFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_circle:
                        position = 1;
                        break;
                    case R.id.rb_cart:
                        position = 2;
                        break;
                    case R.id.rb_user:
                        position = 3;
                        break;
                    default:
                        position = 0;   //如果都不是初始为0，及主页；
                        break;
                }
                /*根据位置取不同位置的fragment*/
                BaseFragment baseFragment = getFragment(position);
                /*切换fragment（上次显示的fragment，当前要显示的fragment）*/
                switchFragment(tempFragemnt, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);     //设置默认加载项home为首页
    }



    /*根据位置得到对应的 Fragment*/
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /*切换fragment,tempFragment 是缓存的fragment*/
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


}