package com.example.zhubangshopingtest1.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.zhubangshopingtest1.MainActivity;
import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.base.BaseFragment;

import com.example.zhubangshopingtest1.home.ErweimaActivity;
import com.example.zhubangshopingtest1.home.adater.HomeFragmentAdapter;
import com.example.zhubangshopingtest1.home.bean.ResultBeanData;
import com.example.zhubangshopingtest1.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


public class HomeFragment extends BaseFragment {
    private static final String TAG =
            HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView ErWeiMa;
    private TextView SaoMa;
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        ErWeiMa = (TextView) view.findViewById(R.id.ErWeiMa);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        SaoMa = (TextView) view.findViewById(R.id.SaoMa);
        //设置图标点击回到顶部事件
        initListener();
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        getDataFromNet();  //联网请求数据；

    }

    private void getDataFromNet(){
        String url = Constants.HOME_URL;
//        String url = "http://100.64.242.6:8080/zhubang/json/HOME_URL.json";    //数据请求成功；
        //使用真机测试时，需要连接在同一个网络，并且关闭防火墙
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public String parseNetworkResponse(Response response, int id) throws IOException {
                        return super.parseNetworkResponse(response, id);
                    }

                    /*请求失败时回调*/
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "请求错误 "+e.getMessage());
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("404");
                        dialog.setMessage("服务器请求失败，请查看网络连接");
                        dialog.setCancelable(false); //点击屏幕或返回键dialog不消失
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.show();
                    }

                    /*联网成功时回调*/
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "数据请求成功" );
                    processData(response);
                    }

                    private void processData(String json) {
                        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
                        resultBean  = resultBeanData.getResult();

                          //测试解析用的
                        Log.e(TAG, "解析数据 "+resultBean.getHot_info().get(0).getName()  );


                        if (resultBean != null){
                            Log.e(TAG, "有数据，进行数据解析中……");
                            //有数据；
                            //设置适配器；
                            adapter = new HomeFragmentAdapter(mContext ,resultBean);
                            rvHome.setAdapter(adapter);
                            GridLayoutManager manager = new GridLayoutManager(mContext,1);
                            //设置跨度大小的监听，用于回到顶部,当recommend模块显示时，进行显示；
                            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    ib_top.setVisibility(View.VISIBLE);
//                                    if (position <= 2) {
//                                        ib_top.setVisibility(View.GONE);
//                                    } else {
//                                        ib_top.setVisibility(View.VISIBLE);
//                                    }
                                    //只能返回 1
                                    return 1;
                                }
                            });
                            //设置布局管理者
                            rvHome.setLayoutManager(manager);
                        }else {
                            //没有数据,弹出提示；
                            Log.e(TAG, "processData: 没有数据");
                        }
                    }


                });
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //二维码的监听
        ErWeiMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ErweimaActivity.class);
                    startActivity(intent);

                Toast.makeText(mContext, "弹出二维码",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //扫码的监听
        SaoMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "打开相机",
                        Toast.LENGTH_SHORT).show();
            }
        });
    } }