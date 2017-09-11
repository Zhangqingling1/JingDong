package com.aqinga.jingdong.presenter;

import android.content.Context;

import com.aqinga.jingdong.app.App;
import com.aqinga.jingdong.view.iview.IView;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/619:51
 */

public abstract class Basepresenter<T extends IView> {
    protected T iview;
    public Basepresenter(T iview) {
        this.iview = iview;
        init();
    }
    protected abstract void init();

    public Context context() {
    if (iview!=null&&iview.context()!=null){
        return iview.context();
    }
        return App.appcontext();
    }
}
