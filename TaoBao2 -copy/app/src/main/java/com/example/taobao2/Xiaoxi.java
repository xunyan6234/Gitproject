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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Xiaoxi extends Fragment {

    private XiaoxiViewModel mViewModel;

    public static Xiaoxi newInstance() {
        return new Xiaoxi();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xiaoxi_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(XiaoxiViewModel.class);
        // TODO: Use the ViewModel

        RelativeLayout huihua1=(RelativeLayout) getActivity().findViewById(R.id.huihua1);
        huihua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会话1",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),huihua1.class);
                startActivity(it_tianmaoxinpin);
            }
        });
        RelativeLayout huihua2=(RelativeLayout) getActivity().findViewById(R.id.huihua2);
        huihua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会话2",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),huihua2.class);
                startActivity(it_tianmaoxinpin);
            }
        });
        RelativeLayout huihua3=(RelativeLayout) getActivity().findViewById(R.id.huihua3);
        huihua3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会话3",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),huihua3.class);
                startActivity(it_tianmaoxinpin);
            }
        });
        RelativeLayout huihua4=(RelativeLayout) getActivity().findViewById(R.id.huihua4);
        huihua4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会话4",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),huihua4.class);
                startActivity(it_tianmaoxinpin);
            }
        });
        RelativeLayout huihua5=(RelativeLayout) getActivity().findViewById(R.id.huihua5);
        huihua5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"会话5",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),huihua5.class);
                startActivity(it_tianmaoxinpin);
            }
        });
    }



}
