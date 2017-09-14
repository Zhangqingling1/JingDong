package com.aqinga.jingdong.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/718:09
 */

public class GouWuCheAdapter extends XRecyclerView.Adapter<GouWuCheAdapter.ViewHolder> {

    List<String> list;
    Context context;
    HashMap<Integer,Boolean> ishashmap;

    public GouWuCheAdapter(List<String> list, Context context, HashMap<Integer, Boolean> ishashmap) {
        this.list = list;
        this.context = context;
        this.ishashmap = ishashmap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_gouwuche, parent, false);
        return new ViewHolder(inflate);
    }
    public interface OnItemLongClickListener{
        void OnItemLongClickListener(View view,int position);
    }
    private OnItemLongClickListener setOnItemLongClickListener;

    public void SetOnItemClickListener(OnItemLongClickListener myOnItemLongClickListener) {
        this.setOnItemLongClickListener = myOnItemLongClickListener;
    }
    //全选
    public Set<Map.Entry<Integer, Boolean>> SelectedAll(){
        Set<Map.Entry<Integer, Boolean>> entries = ishashmap.entrySet();
        boolean showhashmap = false;
        for (Map.Entry<Integer, Boolean> entry: entries) {
            Boolean value = entry.getValue();
            if (!value){
                showhashmap=true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry: entries){
            entry.setValue(showhashmap);
        }
        notifyDataSetChanged();
        return entries;
    }
    //反选
    public void SelectedRever(){
        Set<Map.Entry<Integer, Boolean>> entries = ishashmap.entrySet();
        for (Map.Entry<Integer, Boolean> entry: entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text.setText(list.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (setOnItemLongClickListener!=null){
                    setOnItemLongClickListener.OnItemLongClickListener(v,position);
                }
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ishashmap.put(position,!ishashmap.get(position));
                notifyDataSetChanged();
            }
        });
        holder.checkBox.setChecked(ishashmap.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{
        View itemView;
         TextView text;
        private final CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            text = (TextView) itemView.findViewById(R.id.text_Item_cost);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_box);
        }
    }
}
