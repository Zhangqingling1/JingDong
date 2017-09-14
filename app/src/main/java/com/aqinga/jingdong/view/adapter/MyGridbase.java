package com.aqinga.jingdong.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.FenLeiBean2;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1219:10
 */

public class MyGridbase extends BaseAdapter {
        Context context;
        List<FenLeiBean2.DatasBean.ClassListBean> list;

    public MyGridbase(Context context, List<FenLeiBean2.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
        public int getCount() {
            return list!=null?list.size():0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewholder vh=null;
            if (convertView==null){
                vh=new viewholder();
                convertView=convertView.inflate(context, R.layout.mygridbase,null);
                vh.tv= (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(vh);
            }else {
                vh= (viewholder) convertView.getTag();
            }
            vh.tv.setText(list.get(position).getGc_name());
            return convertView;
        }
        class viewholder{
            TextView tv;
        }
    }
