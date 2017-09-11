package com.aqinga.jingdong.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aqinga.jingdong.R;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/615:23
 */

public class Home_Page_Fragment extends Fragment {

    private Banner banner;
    Integer [] arr = {R.drawable.jsbundles_jdreactintlbrand_images_brand_enter_price,
    R.drawable.jsbundles_jdreactintlbrand_images_brand_enter_advance_bg,
    R.drawable.jsbundles_jdreactintlbrand_images_brand_promotions_ads};
    List<Integer> list;
    private ViewPager viewpager;
    private RadioGroup group;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        banner = (Banner) view.findViewById(R.id.banner);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        group = (RadioGroup) view.findViewById(R.id.radio_group);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radio_button1);
        radiobutton2 = (RadioButton) view.findViewById(R.id.radio_button2);
        viewpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new Home_Page_item1();
                        break;
                    case 1:
                        fragment = new Home_Page_item2();
                        break;
                }
                return fragment;
            }
            @Override
            public int getCount() {
                return 2;
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                group.check(group.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //轮播图
        list= new ArrayList<>();
        for (Integer a:arr) {
            list.add(a);
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(list);
//        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
//        banner.setDelayTime(2000);
        banner.start();
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }
}
