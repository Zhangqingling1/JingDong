package com.aqinga.jingdong.model;

import android.support.annotation.NonNull;

import com.aqinga.jingdong.model.bean.DataBean;

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
    //注册操作
    public void zhuce(@NonNull final zhuceclick<DataBean> beanzhuceclick){
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
                        beanzhuceclick.zhucechenggong(bean);
                    }else {
                        String message = connection.getResponseMessage();
                        beanzhuceclick.zhuceshibai(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface zhuceclick<T>{
        void  zhucechenggong(DataBean bean);
        void zhuceshibai(String message);
    }
}
