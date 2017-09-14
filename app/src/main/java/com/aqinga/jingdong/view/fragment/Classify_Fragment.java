package com.aqinga.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FenLeiBean1;
import com.aqinga.jingdong.model.utils.OkHttpUtils;
import com.aqinga.jingdong.view.adapter.MyVerticalpager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/616:06
 */

//京东分类
public class Classify_Fragment extends Fragment {
    private VerticalTabLayout tab;
    private ViewPager pager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        tab = (VerticalTabLayout) view.findViewById(R.id.tablayout_vertical);
        pager = (ViewPager) view.findViewById(R.id.vertical_viewpager);
        pager.setOffscreenPageLimit(14);
        init();

    }
    public void init(){
        OkHttpUtils.sendOkHttpRequest("http://169.254.29.75/mobile/index.php?act=goods_class", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                FenLeiBean1 fenLeiBean1 = gson.fromJson(json, FenLeiBean1.class);
                final List<FenLeiBean1.DatasBean.ClassListBean> list = fenLeiBean1.getDatas().getClass_list();
                pager.setAdapter(new MyVerticalpager(getChildFragmentManager(),list));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tab.setupWithViewPager(pager);
                    }
                });

            }
        });
    }
}

