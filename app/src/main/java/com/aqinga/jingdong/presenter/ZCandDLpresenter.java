package com.aqinga.jingdong.presenter;

import com.aqinga.jingdong.model.RegisterModel;
import com.aqinga.jingdong.model.bean.DataBean;
import com.aqinga.jingdong.view.iview.DLandZCView;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/618:23
 */

public class ZCandDLpresenter extends Basepresenter<DLandZCView> {

    private RegisterModel model;

    public ZCandDLpresenter(DLandZCView iview) {
        super(iview);
        init();
    }    @Override
    protected void init() {
        model = new RegisterModel();
    }

    public void denglu(){
        model.denglu(new RegisterModel.dengluclick<DataBean>() {
            @Override
            public void dengluchenggong(DataBean bean) {
                iview.dengluchenggong(bean);
            }

            @Override
            public void denglushibai(String message) {
                iview.denglushibai(message);
            }
        });
    }

}
