package com.example.zhubangshopingtest1.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.zhubangshopingtest1.app.MyApplication;
import com.example.zhubangshopingtest1.home.bean.GoodsBean;
import com.example.zhubangshopingtest1.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private final Context mContext;
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context){
        this.mContext = context;
        //把之前存储的数据读取出来
        sparseArray = new SparseArray<>(100);
        //从本地读取数据加入SparseArray中
        listToSparseArray();
    }

    //从本地读取数据加入SparseArray中
    private void listToSparseArray(){
        List<GoodsBean> goodsBeanList = getAllData();
        /*把list数据转换成SparseArray*/
        for ( int i = 0 ; i < goodsBeanList.size(); i++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }

    /*获取本地所有数据*/
    public List<GoodsBean> getAllData(){
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        /*1.从本地获取*/
        String json = CacheUtils.getString(mContext,JSON_CART);
        /*2.使用Gson转换为列表*/
        /*判断不为空时执行，防止控指针*/
        if (!TextUtils.isEmpty(json)){
            /*把String转换成list*/
            goodsBeanList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }

    /*得到购物车实例*/
    public static CartStorage getInstance(){
        if (instance == null){
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    /*添加数据*/
    public void addData(GoodsBean goodsBean){
        /*1.添加到内存中SparseArray，如果当前数据已经存在，就修改为number递增*/
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData != null){
            /*内存中有数据时*/
            tempData.setNumber(tempData.getNumber()+1);
        }else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        /*同步到内存*/
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);
        /*2.同步到本地*/
        saveLocal();
    }

    /*删除数据*/
    public void deleteData(GoodsBean goodsBean){
        /*在内存中删除*/
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        /*从内存保存到本地*/
        saveLocal();
    }
    /*改数据*/
    public void updateData(GoodsBean goodsBean){
        /*在内存中更新*/
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        /*从内存保存到本地*/
        saveLocal();
    }
    private void saveLocal(){
        /*SparseArray转换成List*/
        List<GoodsBean> goodsBeanList = sparseToList();
        /*Gson把列表转换成String类型*/
        String json = new Gson().toJson(goodsBeanList);
        /*把String类型数据保存*/
        CacheUtils.saveString(mContext,JSON_CART,json);
    }
    private List<GoodsBean> sparseToList(){
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i<sparseArray.size();i++){
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }
}