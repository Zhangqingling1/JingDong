package com.aqinga.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.view.adapter.MyPager;
import com.library.zxing.activity.QRCodeScanFragment;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/616:06
 */

//发现
public class Discover_Fragment extends QRCodeScanFragment implements View.OnClickListener {


    private TabLayout tab;
    private ViewPager pager;
    private ImageView erweima;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        tab = (TabLayout) view.findViewById(R.id.tab);
        erweima = (ImageView) view.findViewById(R.id.erweima);
        erweima.setOnClickListener(this);
        pager = (ViewPager) view.findViewById(R.id.viewpager1);
        pager.setOffscreenPageLimit(12);
        pager.setAdapter(new MyPager(getActivity().getSupportFragmentManager()));
        tab.setupWithViewPager(pager);

    }

    @Override
    public void onClick(View v) {
        startScanQRCode();
    }
}
