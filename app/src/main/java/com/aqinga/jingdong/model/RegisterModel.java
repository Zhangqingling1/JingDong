package com.aqinga.jingdong.model;

import android.support.annotation.NonNull;

import com.aqinga.jingdong.model.bean.DataBean;
import com.aqinga.jingdong.model.bean.DbBean;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/618:26
 */

public class RegisterModel {
    public RegisterModel() {
    }
    //登录操作
    public void denglu(@NonNull final dengluclick<DataBean> beandengluclick){
        new Thread(new Runnable() {

            private HttpURLConnection connection;

            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code==200){
                        DataBean bean = new DataBean("注册成功");
                        DbBean bean1 = new DbBean();

                        beandengluclick.dengluchenggong(bean);
                    }else {
                        String message = connection.getResponseMessage();
                        beandengluclick.denglushibai(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface dengluclick<T>{
        void  dengluchenggong(DataBean bean);
        void denglushibai(String message);
    }
}
