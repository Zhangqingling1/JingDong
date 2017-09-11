package com.aqinga.jingdong.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.aqinga.jingdong.view.fragment.MytabFragment;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/620:23
 */

public class MyPager extends FragmentPagerAdapter {
    private String[] title = {"精选","直播","订阅","视频购","问答","清单","社区","生活","数码","亲子","风尚","美食"};
    private FragmentManager manager;
    public MyPager(FragmentManager fm) {
        super(fm);
        manager = fm;
    }
      @Override
    public int getCount() {
        return title!=null?title.length:0;
    }
    @Override
    public Fragment getItem(int position) {
        MytabFragment fragment = new MytabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text",title[position]);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
