package com.aqinga.jingdong.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.GoodsBean;
import com.aqinga.jingdong.model.bean.StoreBean;
import com.aqinga.jingdong.view.adapter.MyGouwucheAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/616:06
 */

//购物车
public class Shopping_cartFragment extends Fragment{

    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();

    MyGouwucheAdapter myBaseExpandableListAdapter;
    CheckBox id_cb_select_all;
    LinearLayout id_ll_normal_all_state;
    LinearLayout id_ll_editing_all_state;
    ExpandableListView expandableListView;
    RelativeLayout id_rl_cart_is_empty;
    RelativeLayout id_rl_foot;

    TextView id_tv_edit_all;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_fragment,container,false);

        //initData();
        initCartData();

        expandableListView = (ExpandableListView) view.findViewById(R.id.id_elv_listview);

        myBaseExpandableListAdapter = new MyGouwucheAdapter(getContext(), parentMapList, childMapList_list);
        expandableListView.setAdapter(myBaseExpandableListAdapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "click：" + position, Toast.LENGTH_SHORT).show();
            }
        });


        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }


        ImageView id_iv_back = (ImageView) view.findViewById(R.id.id_iv_back);
        id_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click :back", Toast.LENGTH_SHORT).show();
            }
        });

        id_ll_normal_all_state = (LinearLayout) view.findViewById(R.id.id_ll_normal_all_state);
        id_ll_editing_all_state = (LinearLayout) view.findViewById(R.id.id_ll_editing_all_state);
        id_rl_cart_is_empty = (RelativeLayout) view.findViewById(R.id.id_rl_cart_is_empty);
        TextView id_tv_save_star_all = (TextView) view.findViewById(R.id.id_tv_save_star_all);
        id_tv_save_star_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "收藏选中多选商品", Toast.LENGTH_SHORT).show();
            }
        });
        TextView id_tv_delete_all = (TextView) view.findViewById(R.id.id_tv_delete_all);
        id_tv_delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBaseExpandableListAdapter.removeGoods();
            }
        });

        id_tv_edit_all = (TextView) view.findViewById(R.id.id_tv_edit_all);

        id_tv_edit_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof TextView) {
                    TextView tv = (TextView) v;
                    if (MyGouwucheAdapter.EDITING.equals(tv.getText())) {
                        myBaseExpandableListAdapter.setupEditingAll(true);
                        tv.setText(MyGouwucheAdapter.FINISH_EDITING);
                        changeFootShowDeleteView(true);
                    } else {
                        myBaseExpandableListAdapter.setupEditingAll(false);
                        tv.setText(MyGouwucheAdapter.EDITING);
                        changeFootShowDeleteView(false);
                    }

                }
            }
        });

        id_cb_select_all = (CheckBox) view.findViewById(R.id.id_cb_select_all);

        id_cb_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    myBaseExpandableListAdapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });

        final TextView id_tv_totalPrice = (TextView) view.findViewById(R.id.id_tv_totalPrice);

        final TextView id_tv_totalCount_jiesuan = (TextView) view.findViewById(R.id.id_tv_totalCount_jiesuan);
        id_tv_totalCount_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "结算成功", Toast.LENGTH_SHORT).show();
            }
        });
        myBaseExpandableListAdapter.setOnGoodsCheckedChangeListener(new MyGouwucheAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                id_tv_totalPrice.setText(String.format(getString(R.string.total), totalPrice));
                id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount));
            }
        });

        myBaseExpandableListAdapter.setOnAllCheckedBoxNeedChangeListener(new MyGouwucheAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                id_cb_select_all.setChecked(allParentIsChecked);
            }
        });

        myBaseExpandableListAdapter.setOnEditingTvChangeListener(new MyGouwucheAdapter.OnEditingTvChangeListener() {
            @Override
            public void onEditingTvChange(boolean allIsEditing) {

                changeFootShowDeleteView(allIsEditing);

            }
        });

        myBaseExpandableListAdapter.setOnCheckHasGoodsListener(new MyGouwucheAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                setupViewsShow(isHasGoods);
            }
        });

        id_rl_foot = (RelativeLayout) view.findViewById(R.id.id_rl_foot);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        id_rl_foot.measure(w, h);
        int r_width = id_rl_foot.getMeasuredWidth();
        int r_height = id_rl_foot.getMeasuredHeight();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int top = dp2px(getContext(), 48);
        lp.setMargins(0, top, 0, r_height);//48

        expandableListView.setLayoutParams(lp);
        if (parentMapList != null && parentMapList.size() > 0) {
            setupViewsShow(true);
        } else {
            setupViewsShow(false);
        }
        return view;
    }

    private void setupViewsShow(boolean isHasGoods) {
        if (isHasGoods) {
            expandableListView.setVisibility(View.VISIBLE);
            id_rl_cart_is_empty.setVisibility(View.GONE);
            id_rl_foot.setVisibility(View.VISIBLE);
            id_tv_edit_all.setVisibility(View.VISIBLE);
        } else {
            expandableListView.setVisibility(View.GONE);
            id_rl_cart_is_empty.setVisibility(View.VISIBLE);
            id_rl_foot.setVisibility(View.GONE);
            id_tv_edit_all.setVisibility(View.GONE);
        }
    }

    public void changeFootShowDeleteView(boolean showDeleteView) {

        if (showDeleteView) {
            id_tv_edit_all.setText(MyGouwucheAdapter.FINISH_EDITING);

            id_ll_normal_all_state.setVisibility(View.INVISIBLE);
            id_ll_editing_all_state.setVisibility(View.VISIBLE);
        } else {
            id_tv_edit_all.setText(MyGouwucheAdapter.EDITING);

            id_ll_normal_all_state.setVisibility(View.VISIBLE);
            id_ll_editing_all_state.setVisibility(View.INVISIBLE);
        }
    }

    public int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void initCartData() {


        for (int i = 0; i < 4; i++) {
            String store="官方旗舰店";
            if(i%2==0){
                store="阿莹的店";
            }
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();

            parentMap.put("parentName", new StoreBean("" + i, store + i, false, false));
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < 3; j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();

                GoodsBean goodsBean = new GoodsBean(i + "_" + j, store+i + "下的物品" + j, "url", "均码，蓝色", 150, 120, 1, GoodsBean.STATUS_VALID, false, false);
                childMap.put("childName", goodsBean);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
    }

    private void initData() {


        for (int i = 0; i < 15; i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            parentMap.put("parentName", "parentName" + i);
            if (i % 2 == 0) {
                parentMap.put("parentIcon", R.mipmap.ic_launcher);
            } else {
                parentMap.put("parentIcon", R.mipmap.louisgeek);
            }
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < 10; j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();
                childMap.put("childName", "parentName" + i + "下面的childName" + j);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
    }

}
