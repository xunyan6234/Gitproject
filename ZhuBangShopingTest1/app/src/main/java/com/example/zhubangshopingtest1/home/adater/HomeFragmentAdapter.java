package com.example.zhubangshopingtest1.home.adater;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.app.GoodsInfoActivity;
import com.example.zhubangshopingtest1.home.bean.GoodsBean;
import com.example.zhubangshopingtest1.home.bean.ResultBeanData;
import com.example.zhubangshopingtest1.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 五种类型
     */
    private static final String GOODS_BEAN = "goodsBean";
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;
    /* *//**
     * 活动
     *//*
    public static final int ACT = 2;
    *//**
     * 秒杀
     *//*
    public static final int SECKILL = 3;*/
    /**
     * 推荐
     */
    public static final int RECOMMEND = 2;
    /* */
    /**
     * 热卖
     *//*
    public static final int HOT = 5;*/
    private LayoutInflater mLayoutInflater;   //初始化布局；
    private Context mContext;
    private ResultBeanData.ResultBean resultBean;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager,
                    null), resultBean);//实现顶部轮播图
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item,
                    null)); //实现分类
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item,
                    null)); //实现推荐
        }
        return null;
    }

    /*得到类型*/
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
           /* case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;*/
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            /*case HOT:
                currentType = HOT;
                break;*/
        }
        return currentType;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }
    }
    /*顶部轮播图*/
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private View itemView;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView, ResultBeanData.ResultBean resultBean) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置banner的数据绑定
            //得到图片地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            /*设置动画效果*/
//            banner.setBannerAnimation(Transformer.Accordion);
            /*设置循环指示点*/
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片
                    Glide.with(mContext).load(Constants.IMAGE_URL + url).into(view);
                }
            });
            /*设置轮播图每个item的点击事件*/
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
                    //推荐商品信息类
                    ResultBeanData.ResultBean.BannerInfoBean bannerInfoBean = banner_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(bannerInfoBean.getImage());
                    goodsBean.setFigure(bannerInfoBean.getImage());
                    goodsBean.setName(bannerInfoBean.getImage());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
    /*分类*/
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemview) {
            super(itemview);
            this.mContext = mContext;
            gv_channel = (GridView) itemview.findViewById(R.id.gv_channel);
            //设置分类的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            /*得到数据，设置GridView适配器*/
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }
    }
    /*热门推荐*/
    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendAdapter adapter;

        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            /*得到数据，设置GridView适配器*/
            adapter = new RecommendAdapter(mContext, recommend_info);
            gv_recommend.setAdapter(adapter);

            //设置推荐的点击事
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
                    //推荐商品信息类
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                    Log.e("TAG", "发送推荐商品详细信息 "+ goodsBean.toString() );
                }
            });
        }
    }
    /**
     * 点击启动商品详情
    * */
    public void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);


    }

    /**
     * 总共有多少个item
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
