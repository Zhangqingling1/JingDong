package com.aqinga.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.view.adapter.FaXianAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        adapter = new FaXianAdapter(list,getContext());
        xrecyclerview.setAdapter(adapter);
        xrecyclerview.setLoadingListener(this);

        return view;
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            list.add("第"+i+"条");
        }
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
