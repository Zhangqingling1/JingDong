package com.aqinga.jingdong.model.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1315:43
 */


public class MyGrideView extends GridView {
    public MyGrideView(Context context) {
        super(context);
    }

    public MyGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGrideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

