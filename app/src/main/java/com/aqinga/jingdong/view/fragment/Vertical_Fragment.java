package com.aqinga.jingdong.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FenLeiBean2;
import com.aqinga.jingdong.model.utils.OkHttpUtils;
import com.aqinga.jingdong.view.adapter.Vertical_Recyclerview_Adapter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

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
 * 2017/9/1216:51
 */

public class Vertical_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;
    private TextView text;
    private String text1;
    private Banner banner;
    Integer [] arr = {R.drawable.jsbundles_jdreactintlbrand_images_brand_enter_price,
            R.drawable.jsbundles_jdreactintlbrand_images_brand_enter_advance_bg,
            R.drawable.jsbundles_jdreactintlbrand_images_brand_promotions_ads};
    List<Integer> list;
    private Vertical_Recyclerview_Adapter pager;
    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_fragment,container,false);
        Bundle arguments = getArguments();
        text1 = arguments.getString("id");
        id = Integer.valueOf(text1).intValue();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        init();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_vertical);
        banner = (Banner) view.findViewById(R.id.banner_vertical);
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
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }
    public void init(){
        OkHttpUtils.sendOkHttpRequest("http://169.254.29.75/mobile/index.php?act=goods_class&gc_id="+id, new Callback() {

            private List<FenLeiBean2.DatasBean.ClassListBean> list1;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                FenLeiBean2 fenLeiBean2 = gson.fromJson(json, FenLeiBean2.class);
                list1 = fenLeiBean2.getDatas().getClass_list();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pager = new Vertical_Recyclerview_Adapter(getActivity(),list1);
                        recyclerView.setAdapter(pager);
                    }
                });

            }
        });
    }

}
