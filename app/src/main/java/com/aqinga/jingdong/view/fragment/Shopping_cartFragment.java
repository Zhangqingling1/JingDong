package com.aqinga.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.view.adapter.GouWuCheAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/616:06
 */

//购物车
public class Shopping_cartFragment extends Fragment implements View.OnClickListener, GouWuCheAdapter.OnItemLongClickListener {
    private XRecyclerView xrecyclerview;
    List<String> list = new ArrayList<>();
    HashMap<Integer,Boolean> ishashmap = new HashMap<>();
    private GouWuCheAdapter adapter;
    private LinearLayoutManager linearlayoutmanager;
    private CheckBox checkBox;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        init();
        xrecyclerview=(XRecyclerView) view.findViewById(R.id.gouwucheview);
        linearlayoutmanager = new LinearLayoutManager(getContext());
        xrecyclerview.setLayoutManager(linearlayoutmanager);
        adapter = new GouWuCheAdapter(list,getContext(),ishashmap);
        checkBox = (CheckBox) view.findViewById(R.id.quanxuan);
        adapter.SetOnItemClickListener(this);
        xrecyclerview.setAdapter(adapter);
//        xrecyclerview.setLoadingListener(this);
        checkBox.setOnClickListener(this);
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            list.add("￥"+i+".00");
            ishashmap.put(i,false);
        }
    }
//
//    @Override
//    public void onRefresh() {
//        list.clear();
//        init();
//        adapter.notifyDataSetChanged();
//        xrecyclerview.refreshComplete();
//    }
//    @Override
//    public void onLoadMore() {
//        init();
//        adapter.notifyDataSetChanged();
//        xrecyclerview.loadMoreComplete();
//    }
    @Override
    public void OnItemLongClickListener(View view, int position) {
        adapter.SelectedRever();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quanxuan:
                adapter.SelectedAll();
                break;
        }
    }
}
