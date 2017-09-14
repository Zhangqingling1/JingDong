package com.aqinga.jingdong.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FaXianBean;
import com.aqinga.jingdong.view.activity.ItemActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/718:09
 */

public class FaXianAdapter extends XRecyclerView.Adapter<FaXianAdapter.ViewHolder> {

    List<FaXianBean.ResultBean.ListBean> list;
    Activity context;

    public FaXianAdapter(List<FaXianBean.ResultBean.ListBean> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_faxian, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textview.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.image);
        holder.shijian.setText(list.get(position).getTime());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        TextView textview;
        private final CardView card;
        private final TextView shijian;
        private final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.tv_content);
            card = (CardView) itemView.findViewById(R.id.card_view);
            image = (ImageView) itemView.findViewById(R.id.faxian_tu);
            shijian = (TextView) itemView.findViewById(R.id.shijian);
        }
    }
}
