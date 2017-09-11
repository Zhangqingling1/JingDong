package com.aqinga.jingdong.model.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/715:02
 */

public class IntegerUtils{
    public static boolean panduan(Context context){

//得到管理网络的服务实例
ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//得到网络信息 (判断网络是否连接、3G、4G)
NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//判断网络是否已经连接，如果连接的话，返回true，否则false
return (networkInfo != null && networkInfo.isConnected());

    }
}
