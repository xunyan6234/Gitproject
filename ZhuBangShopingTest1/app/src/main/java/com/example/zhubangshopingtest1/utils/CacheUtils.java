package com.example.zhubangshopingtest1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/*缓存工具类*/
public class CacheUtils {

    /*得到的String类型的数据*/
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("zhubang",Context.MODE_PRIVATE);
        sp.getString(key,"");
        return sp.getString(key, "");
    }

    /*保存的String类型的数据*/
    public static void saveString(Context context,String key ,String value) {
        SharedPreferences sp = context.getSharedPreferences("zhubang",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
