package com.aqinga.jingdong.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FenLeiBean2;
import com.aqinga.jingdong.model.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/718:09
 */

public class Vertical_Recyclerview_Adapter extends RecyclerView.Adapter<Vertical_Recyclerview_Adapter.ViewHolder> {
    private Activity context;
    private List<FenLeiBean2.DatasBean.ClassListBean> list;

    public Vertical_Recyclerview_Adapter(Activity context, List<FenLeiBean2.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_recyclerview, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getGc_name());
        String id = list.get(position).getGc_id();
        OkHttpUtils.sendOkHttpRequest("http://169.254.29.75/mobile/index.php?act=goods_class&gc_id="+id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                FenLeiBean2 fenLeiBean2 = gson.fromJson(json, FenLeiBean2.class);
                 final List<FenLeiBean2.DatasBean.ClassListBean> data = fenLeiBean2.getDatas().getClass_list();

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyGridbase myGridbase = new MyGridbase(context,data);
                        holder.gridView.setAdapter(myGridbase);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

            private final GridView gridView;
            private final TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view_vertical);
            gridView = (GridView) itemView.findViewById(R.id.grid_view);
        }
    }
}
