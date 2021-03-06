package com.example.taobao2;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Wode extends Fragment {


    private WodeViewModel mViewModel;

    public static Wode newInstance() {
        return new Wode();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wode_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WodeViewModel.class);
        // TODO: Use the ViewModel
        ImageButton bt_daishouhuo =(ImageButton) getActivity().findViewById(R.id.daishouhuo);
        bt_daishouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"待收货",Toast.LENGTH_LONG).show();
                Intent it_daishouhuo=new Intent();
                it_daishouhuo.setClass(getActivity(),daishouhuo.class);
                startActivity(it_daishouhuo);
            }
        });
        ImageButton bt_daifahuo =(ImageButton) getActivity().findViewById(R.id.daifahuo);
        bt_daifahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"待发货",Toast.LENGTH_LONG).show();
                Intent it_daifahuo=new Intent();
                it_daifahuo.setClass(getActivity(),daifahuo.class);
                startActivity(it_daifahuo);
            }
        });
        ImageButton bt_daifukuan =(ImageButton) getActivity().findViewById(R.id.daifukuan);
        bt_daifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"待付款",Toast.LENGTH_LONG).show();
                Intent it_daifukuan=new Intent();
                it_daifukuan.setClass(getActivity(),daifukuan.class);
                startActivity(it_daifukuan);
            }
        });
        ImageButton bt_pingjia =(ImageButton) getActivity().findViewById(R.id.pingjia);
        bt_pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"评价",Toast.LENGTH_LONG).show();
                Intent it_pingjia=new Intent();
                it_pingjia.setClass(getActivity(),pingjia.class);
                startActivity(it_pingjia);
            }
        });
        ImageButton bt_tuikuan =(ImageButton) getActivity().findViewById(R.id.tuikuan);
        bt_tuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"退款/售后",Toast.LENGTH_LONG).show();
                Intent it_tuikuan=new Intent();
                it_tuikuan.setClass(getActivity(),tuikuan.class);
                startActivity(it_tuikuan);
            }
        });
        TextView bt_quanbudingdan =(TextView) getActivity().findViewById(R.id.quanbudingdan);
        bt_quanbudingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"全部订单",Toast.LENGTH_LONG).show();
                Intent it_quanbudingdan=new Intent();
                it_quanbudingdan.setClass(getActivity(),quanbudingdan.class);
                startActivity(it_quanbudingdan);
            }
        });

        TextView bt_huiyuanzhongxin =(TextView) getActivity().findViewById(R.id.huiyuanzhongxin);
        bt_huiyuanzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会员中心",Toast.LENGTH_LONG).show();
                Intent it_huiyuanzhongxin=new Intent();
                it_huiyuanzhongxin.setClass(getActivity(),huiyuanzhongxin.class);
                startActivity(it_huiyuanzhongxin);
            }
        });
        ImageView bt_huiyuanzhongxin_i =(ImageView) getActivity().findViewById(R.id.huiyuanzhongxin_i);
        bt_huiyuanzhongxin_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会员中心",Toast.LENGTH_LONG).show();
                Intent it_huiyuanzhongxin=new Intent();
                it_huiyuanzhongxin.setClass(getActivity(),huiyuanzhongxin.class);
                startActivity(it_huiyuanzhongxin);
            }
        });

        TextView bt_tianmaojifen =(TextView) getActivity().findViewById(R.id.tianmaojifen);
        bt_tianmaojifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"天猫积分",Toast.LENGTH_LONG).show();
                Intent it_tianmaojifen=new Intent();
                it_tianmaojifen.setClass(getActivity(),tianmaojifen.class);
                startActivity(it_tianmaojifen);
            }
        });
        ImageView bt_tianmaojifen_i =(ImageView) getActivity().findViewById(R.id.tianmaojifen_i);
        bt_tianmaojifen_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"天猫积分",Toast.LENGTH_LONG).show();
                Intent it_tianmaojifen=new Intent();
                it_tianmaojifen.setClass(getActivity(),tianmaojifen.class);
                startActivity(it_tianmaojifen);
            }
        });

        TextView bt_hongbaoshengqianka =(TextView) getActivity().findViewById(R.id.hongbaoshengqianka);
        bt_hongbaoshengqianka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"红包省钱卡",Toast.LENGTH_LONG).show();
                Intent it_hongbaoshengqianka=new Intent();
                it_hongbaoshengqianka.setClass(getActivity(),hongbaoshengqianka.class);
                startActivity(it_hongbaoshengqianka);
            }
        });
        ImageView bt_hongbaoshengqianka_i =(ImageView) getActivity().findViewById(R.id.hongbaoshengqianka_i);
        bt_hongbaoshengqianka_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"红包省钱卡",Toast.LENGTH_LONG).show();
                Intent it_hongbaoshengqianka=new Intent();
                it_hongbaoshengqianka.setClass(getActivity(),hongbaoshengqianka.class);
                startActivity(it_hongbaoshengqianka);
            }
        });

        TextView bt_hongbaoqiandao =(TextView) getActivity().findViewById(R.id.hongbaoqiandao);
        bt_hongbaoqiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"红包签到",Toast.LENGTH_LONG).show();
                Intent it_hongbaoqiandao=new Intent();
                it_hongbaoqiandao.setClass(getActivity(),hongbaoqiandao.class);
                startActivity(it_hongbaoqiandao);
            }
        });
        ImageView bt_hongbaoqiandao_i =(ImageView) getActivity().findViewById(R.id.hongbaoqiandao_i);
        bt_hongbaoqiandao_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"红包签到",Toast.LENGTH_LONG).show();
                Intent it_hongbaoqiandao=new Intent();
                it_hongbaoqiandao.setClass(getActivity(),hongbaoqiandao.class);
                startActivity(it_hongbaoqiandao);
            }
        });


    }

}
