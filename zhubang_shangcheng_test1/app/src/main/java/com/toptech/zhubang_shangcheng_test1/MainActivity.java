package com.toptech.zhubang_shangcheng_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation =(AHBottomNavigation) findViewById(R.id.bottom_navigation);

        //创建items，这里接收3个参数，分别是item的文字，item的icon，选中item时的整体颜色（该项需要开启）
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.icon_rate, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.icon_rate, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.icon_rate, R.color.color_tab_3);

        //添加items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        //设置整体背景颜色（如果开启了单个的背景颜色，该项将会无效）
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        //设置item被选中和待选时的颜色
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

//        //强制绘图（针对带字的icon，测试时出现了bug，导致item不被选时也有颜色，未解决）
//        bottomNavigation.setForceTint(true);
//
        //设置item文字状态

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//
//        //是否开启切换item切换颜色
//        bottomNavigation.setColored(true);

        //设置初始选中的item
        bottomNavigation.setCurrentItem(1);

        //创建、添加通知（小红点），可使用builder构建
        // 此处报错
//        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//        bottomNavigation.setNotification("1", 3);
        // OR
        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
                .build();
        bottomNavigation.setNotification(notification, 1);

        //使某个item有效或者无效（无法被选），并可以设置颜色
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //点击item时的事件
                return true;
            }
        });
    }
}