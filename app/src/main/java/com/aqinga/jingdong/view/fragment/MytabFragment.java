package com.aqinga.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FaXianBean;
import com.aqinga.jingdong.model.utils.OkHttpUtils;
import com.aqinga.jingdong.view.adapter.FaXianAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/620:31
 */

public class MytabFragment extends Fragment implements XRecyclerView.LoadingListener {

    XRecyclerView xrecyclerview;
    List<String> list = new ArrayList<>();
    private FaXianAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mytabfragment,container,false);

        init();

        xrecyclerview=(XRecyclerView) view.findViewById(R.id.faxianxrecyclerview);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getContext());
        xrecyclerview.setLayoutManager(linearlayoutmanager);
        xrecyclerview.setLoadingListener(this);

        return view;
    }

    private void init() {
        OkHttpUtils.sendOkHttpRequest("http://api.jisuapi.com/news/get?channel=%E5%A4%B4%E6%9D%A1&start=0&num=10&appkey=f8aad9299757d186", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                FaXianBean faXianBean = gson.fromJson(json, FaXianBean.class);
                final List<FaXianBean.ResultBean.ListBean> listbean = faXianBean.getResult().getList();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new FaXianAdapter(listbean,getActivity());
                        xrecyclerview.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    public void onRefresh() {
        init();
        adapter.notifyDataSetChanged();
        xrecyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        init();
        adapter.notifyDataSetChanged();
        xrecyclerview.loadMoreComplete();
    }
}
