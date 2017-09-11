package com.aqinga.jingdong.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.view.activity.ItemActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/718:09
 */

public class FaXianAdapter extends XRecyclerView.Adapter<FaXianAdapter.ViewHolder> {

    List<String> list;
    Context context;

    public FaXianAdapter(List<String> list, Context context) {
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
        holder.textview.setText(list.get(position));
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

        public ViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.tv_content);
            card = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
