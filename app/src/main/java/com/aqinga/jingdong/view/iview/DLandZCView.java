package com.aqinga.jingdong.view.iview;

import com.aqinga.jingdong.model.bean.DataBean;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/618:27
 */

public interface DLandZCView<T> extends IView {
    void dengluchenggong(DataBean bean);
    void  denglushibai(String message);
}
