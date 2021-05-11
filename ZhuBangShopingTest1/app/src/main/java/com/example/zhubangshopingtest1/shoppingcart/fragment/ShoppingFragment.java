package com.example.zhubangshopingtest1.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.base.BaseFragment;
import com.example.zhubangshopingtest1.home.bean.GoodsBean;
import com.example.zhubangshopingtest1.shoppingcart.Adapter.ShoppingAdapter;
import com.example.zhubangshopingtest1.shoppingcart.utils.CartStorage;

import java.util.List;

public class ShoppingFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
//    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;

    private static final String TAG =
            ShoppingFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.fragment_shopping_cart,null);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
//        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        ll_empty_shopcart=(LinearLayout) view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
//        btnCollection.setOnClickListener(this);

        initListnter();

        Log.e(TAG, "购物车ui被初始化了");
        return view;
    }

    private void initListnter(){
        /*设置默认的编辑状态*/
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT){
                    /*切换为完成状态*/
                    showDelete();
                }else {
                    /*切换为编辑状态*/
                    hideDelelete();
                }
            }

            private void hideDelelete() {
                /**
                 *1. 设置状态和文本-编辑
                 * 2.变成非勾选
                 * 3. 删除视图显示
                 * 4. 结算视图影藏
                 * */
                tvShopcartEdit.setTag(ACTION_EDIT);
                tvShopcartEdit.setText("编辑");
                if (adapter != null){
                    adapter.chekAll_none(true);
                    adapter.chekAll();
                    adapter.showTotalPrice();
                }
                llDelete.setVisibility(View.GONE);
                llCheckAll.setVisibility(View.VISIBLE);
            }

            private void showDelete() {
                /**
                 *1. 设置状态和文本
                 * 2.变成非勾选
                 * 3. 删除视图显示
                 * 4. 结算视图影藏
                 * */
                tvShopcartEdit.setTag(ACTION_COMPLETE);
                tvShopcartEdit.setText("完成");
                if (adapter != null){
                    adapter.chekAll_none(false);
                    adapter.chekAll();
                }
                llDelete.setVisibility(View.VISIBLE);
                llCheckAll.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
            /*删除选中的*/
            adapter.deleteData();
            /*校验状态*/
            adapter.chekAll();
            //数据大小为0
            if (adapter.getItemCount()==0){
                emptyShoppingCart();
            }
        }
//        else if (v == btnCollection) {
//            // Handle clicks for btnCollection
//        }
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "购物车数据被初始化了");
//        textView.setText("购物车");


    }

//
    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    /*显示数据*/
    private void showData(){
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        if (goodsBeanList != null && goodsBeanList.size()>0){
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);
            /*有数据，把显示的布局影藏，设置适配器*/
            ll_empty_shopcart.setVisibility(View.GONE);
            adapter = new ShoppingAdapter(mContext,goodsBeanList,tvShopcartTotal,checkboxAll,cbAll);
            recyclerview.setAdapter(adapter);
            /*设置布局管理器*/
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }else {
            /*没有数据，显示empty布局*/
            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {
        ll_empty_shopcart.setVisibility(textView.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }
}