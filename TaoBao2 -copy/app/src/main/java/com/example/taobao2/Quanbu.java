package com.example.taobao2;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Quanbu extends Fragment {



    private QuanbuViewModel mViewModel;

    public static Quanbu newInstance() {
        return new Quanbu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.quanbu_fragment, null);
        setView();
        return mView;
    }

    //轮播图

    private View mView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;

    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "烤箱",
            "皮套",
            "空调",
            "饼干",
            "热水器"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    private void setView(){
        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));
        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);
        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_on);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_off);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /*定义的适配器*/

    public class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override

        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
////          view.removeView(view.getChildAt(position));
////          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override

        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    /**

     * 利用线程池定时执行动画轮播

     */

    @Override

    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    /**

     * 图片轮播任务

     * @author liuyazhuang

     *

     */
    private class ViewPageTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**

     * 接收子线程传递过来的数据

     */
    private Handler mHandler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }



    //中部菜单栏

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuanbuViewModel.class);
        // TODO: Use the ViewModel
        RadioButton rb_tianmaoxinpin =(RadioButton) getActivity().findViewById(R.id.tianmaoxinpin);
        rb_tianmaoxinpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"天猫新品",Toast.LENGTH_LONG).show();
                Intent it_tianmaoxinpin=new Intent();
                it_tianmaoxinpin.setClass(getActivity(),tianmaoxinpin.class);
                startActivity(it_tianmaoxinpin);
            }
        });
        RadioButton rb_jinribaokuan =(RadioButton) getActivity().findViewById(R.id.jinribaokuan);
        rb_jinribaokuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"今日爆款",Toast.LENGTH_LONG).show();
                Intent it_jinribaokuan=new Intent();
                it_jinribaokuan.setClass(getActivity(),jinribaokuan.class);
                startActivity(it_jinribaokuan);
            }
        });
        RadioButton rb_tianmaoguoji =(RadioButton) getActivity().findViewById(R.id.tianmaoguoji);
        rb_tianmaoguoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"天猫国际",Toast.LENGTH_LONG).show();
                Intent it_tianmaoguoji=new Intent();
                it_tianmaoguoji.setClass(getActivity(),tianmaoguoji.class);
                startActivity(it_tianmaoguoji);
            }
        });
        RadioButton rb_eleme =(RadioButton) getActivity().findViewById(R.id.eleme);
        rb_eleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"饿了么",Toast.LENGTH_LONG).show();
                Intent it_eleme=new Intent();
                it_eleme.setClass(getActivity(),eleme.class);
                startActivity(it_eleme);
            }
        });
        RadioButton rb_tianmaochaoshi =(RadioButton) getActivity().findViewById(R.id.tianmaochaoshi);
        rb_tianmaochaoshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"天猫超市",Toast.LENGTH_LONG).show();
                Intent it_tianmaochaoshi=new Intent();
                it_tianmaochaoshi.setClass(getActivity(),tianmaochaoshi.class);
                startActivity(it_tianmaochaoshi);
            }
        });
        RadioButton rb_chongzhizhongxin =(RadioButton) getActivity().findViewById(R.id.chongzhizhongxin);
        rb_chongzhizhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"充值中心",Toast.LENGTH_LONG).show();
                Intent it_chongzhizhongxin=new Intent();
                it_chongzhizhongxin.setClass(getActivity(),chongzhizhongxin.class);
                startActivity(it_chongzhizhongxin);
            }
        });
        RadioButton rb_jipiaojiudian =(RadioButton) getActivity().findViewById(R.id.jipiaojiudian);
        rb_jipiaojiudian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"机票酒店",Toast.LENGTH_LONG).show();
                Intent it_jipiaojiudian=new Intent();
                it_jipiaojiudian.setClass(getActivity(),jipiaojiudian.class);
                startActivity(it_jipiaojiudian);
            }
        });
        RadioButton rb_lingtaojinbi =(RadioButton) getActivity().findViewById(R.id.lingtaojinbi);
        rb_lingtaojinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"领淘金币",Toast.LENGTH_LONG).show();
                Intent it_lingtaojinbi=new Intent();
                it_lingtaojinbi.setClass(getActivity(),lingtaojinbi.class);
                startActivity(it_lingtaojinbi);
            }
        });
        RadioButton rb_alipaimai =(RadioButton) getActivity().findViewById(R.id.alipaimai);
        rb_alipaimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"阿里拍卖",Toast.LENGTH_LONG).show();
                Intent it_alipaimai=new Intent();
                it_alipaimai.setClass(getActivity(),alipaimai.class);
                startActivity(it_alipaimai);
            }
        });
        RadioButton rb_taobaochihuo =(RadioButton) getActivity().findViewById(R.id.taobaochihuo);
        rb_taobaochihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"淘宝吃货",Toast.LENGTH_LONG).show();
                Intent it_taobaochihuo=new Intent();
                it_taobaochihuo.setClass(getActivity(),taobaochihuo.class);
                startActivity(it_taobaochihuo);
            }
        });
    }






}
