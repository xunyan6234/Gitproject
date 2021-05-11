package com.example.zhubangshopingtest1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.home.bean.GoodsBean;
import com.example.zhubangshopingtest1.shoppingcart.utils.CartStorage;
import com.example.zhubangshopingtest1.utils.Constants;

public class GoodsInfoActivity extends Activity implements View.OnClickListener{


    private ImageButton ibGoodInfoBack;
    private ImageButton tvMoreShare;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private GoodsBean goods_bean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2021-05-07 11:19:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        tvMoreShare = (ImageButton)findViewById( R.id.tv_more_share );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );          //返回
        tvMoreShare.setOnClickListener( this );             //分享
        btnGoodInfoAddcart.setOnClickListener( this );      //加入购物车
        tvGoodInfoCallcenter.setOnClickListener( this );     //联系客服
        tvGoodInfoCollection.setOnClickListener( this );     //喜欢
        tvGoodInfoCart.setOnClickListener( this );          //进入购物车
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2021-05-07 11:19:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            finish();
            // Handle clicks for ibGoodInfoBack
            //详情页左上角返回上一层
        } else if ( v == tvMoreShare ) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
            // Handle clicks for tvMoreShare
            //详情页右上角分享
        } else if ( v == btnGoodInfoAddcart ) {
            CartStorage.getInstance().addData(goods_bean);
            Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
            // Handle clicks for btnGoodInfoAddcart
        }else if ( v == tvGoodInfoCallcenter ) {
            Toast.makeText(this, "客服", Toast.LENGTH_SHORT).show();
            // Handle clicks for btnGoodInfoAddcart
        }else if ( v == tvGoodInfoCollection ) {
            Toast.makeText(this, "喜欢", Toast.LENGTH_SHORT).show();
            // Handle clicks for btnGoodInfoAddcart
        }else if ( v == tvGoodInfoCart ) {
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
            // Handle clicks for btnGoodInfoAddcart
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        //接收数据
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goodsBean");

        if(goods_bean != null){
            Log.e("TAG", "接收到商品详细信息onCreate: "+goods_bean.toString() );
//            Toast.makeText(this, "goodsbean", Toast.LENGTH_SHORT).show();
            //如果接收到的数据不为空，则设置数据。
            setDataForView(goods_bean);
        }
    }
    private  void setDataForView(GoodsBean goods_bean){
        String name = goods_bean.getName();
        String cover_price = goods_bean.getCover_price();
        String figure = goods_bean.getFigure();
        String product_id = goods_bean.getProduct_id();
        Glide.with(this).load(Constants.IMAGE_URL +
                figure).into(ivGoodInfoImage);
        if (name != null) {
            //设置名称
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            //设置价格
            tvGoodInfoPrice.setText("￥" + cover_price);
        }
    }
}