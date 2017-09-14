package com.aqinga.jingdong.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aqinga.jingdong.model.bean.FenLeiBean1;
import com.aqinga.jingdong.view.fragment.Vertical_Fragment;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1216:47
 */

public class MyVerticalpager extends FragmentPagerAdapter {

    List<FenLeiBean1.DatasBean.ClassListBean> list;

    public MyVerticalpager(FragmentManager fm, List<FenLeiBean1.DatasBean.ClassListBean> list) {
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position) {
        Vertical_Fragment fragment = new Vertical_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",list.get(position).getGc_id());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getGc_name();
    }
}
