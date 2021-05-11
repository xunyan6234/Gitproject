package com.example.zhubangshopingtest1.shoppingcart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zhubangshopingtest1.R;
import com.example.zhubangshopingtest1.home.bean.GoodsBean;
import com.example.zhubangshopingtest1.shoppingcart.View.AddSubView;
import com.example.zhubangshopingtest1.shoppingcart.utils.CartStorage;
import com.example.zhubangshopingtest1.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox cbAll;   //完成状态的删除CheckBox

    public ShoppingAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll,CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll=cbAll;
        showTotalPrice();

        /*设置点击事件*/
        setListener();
        /*刚开始时就校验购物车是否全选*/
        chekAll();
    }

    private void setListener(){
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /**1.根据位置找到对应的bean对象
                 * 2.设置取反的状态
                 * 3.刷新状态
                 * 3.效验是否全选
                 * 5.重新计算价格
                * **/
                GoodsBean goodsBean = datas.get(position);
                goodsBean.setSelected(!goodsBean.isSelected());
                notifyItemChanged(position);
                chekAll();
                showTotalPrice();
            }
        });
        /*设置全选点击事件*/
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**1.得到状态
                 * 2.根据状态设置全选和非全选
                 * 3.计算总价格
                * */
                boolean isCheck = checkboxAll.isChecked();
                chekAll_none(isCheck);
                showTotalPrice();
            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**1.得到状态
                 * 2.根据状态设置全选和非全选
                 * */
                boolean isCheck = cbAll.isChecked();
                chekAll_none(isCheck);
            }
        });
    }

    /*设置全选和非全选*/
    public void chekAll_none(boolean isCheck){
        if (datas !=null && datas.size()>0){
            for (int i =0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void chekAll() {
        if (datas !=null && datas.size()>0){
            int number = 0;
            for (int i =0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isSelected()){
                    /*非全选*/
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else{
                    /*选中的*/
                    number++;
                }
            }
            if (number == datas.size()) {
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }else {
                checkboxAll.setChecked(false);
                cbAll.setChecked(false);
            }
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    /*计算总价格*/
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()) {
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }
        Log.e("TAG", "getTotalPrice: 打印总价格" + totalPrice);
        return totalPrice;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        /*根据位置得到Bean对象*/
        GoodsBean goodsBean = datas.get(position);

        /*设置数据*/
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.IMAGE_URL + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.ddSubView.setValue(goodsBean.getNumber());
        holder.ddSubView.setMinValue(1);    //设置购物车商品下限为1
        holder.ddSubView.setMaxValue(8);    //设置购物车商品上限为8

        /*设置商品数量的变化*/
        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                /*内存中更新*/
                goodsBean.setNumber(value);
                /*本地要更新*/
                CartStorage.getInstance().updateData(goodsBean);
                /*刷新适配器*/
                notifyItemChanged(position);
                /*再次计算价格*/
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if (datas != null && datas.size()>0){
            for (int i = 0; i<datas.size();i++){
                //删除选中的
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()){
                    //内存移除
                    datas.remove(goodsBean);
                    //保存到本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }

            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            ddSubView = (AddSubView) itemView.findViewById(R.id.numberAddSubView);

            //设置item的点击事情
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /*点击购物车物品左边item监听者*/
    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    /*设置item的监听*/
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
