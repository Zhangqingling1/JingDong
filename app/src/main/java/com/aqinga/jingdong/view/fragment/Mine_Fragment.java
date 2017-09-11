package com.aqinga.jingdong.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.presenter.ZCandDLpresenter;
import com.aqinga.jingdong.view.activity.MyActivity;
import com.bumptech.glide.Glide;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/616:06
 */

//我的
public class Mine_Fragment extends Fragment implements View.OnClickListener {

    private TextView zd;
    private ZCandDLpresenter dLpresenter;
    private ImageView image;
    private ImageView image_touxiang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        zd = (TextView) view.findViewById(R.id.text_view);
        image = (ImageView) view.findViewById(R.id.image_view);
        zd.setOnClickListener(this);
        image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_view:
                tiaozhuan();
                break;
            case R.id.text_view:
                tiaozhuan();
                break;
        }
    }
    public void tiaozhuan(){
        Intent intent = new Intent(getActivity(), MyActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences key = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE);
        String tu = key.getString("tu", "http://pic.qqtn.com/up/2016-2/2016021809462323636.jpg");
        Glide.with(getActivity()).load(tu).into(image);
    }
}
