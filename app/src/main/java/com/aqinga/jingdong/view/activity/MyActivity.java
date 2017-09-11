package com.aqinga.jingdong.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.DataBean;
import com.aqinga.jingdong.presenter.ZCandDLpresenter;
import com.aqinga.jingdong.view.iview.DLandZCView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/618:31
 */

public class MyActivity extends AppCompatActivity implements DLandZCView {

    private ZCandDLpresenter zCandDLpresenter;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        zCandDLpresenter = new ZCandDLpresenter(this);
        SharedPreferences sp = getSharedPreferences("key",MODE_PRIVATE);
        editor = sp.edit();
    }

    //手机快速注册
    public void zhuce(View view) {
        zCandDLpresenter.zhuce();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void zhucechenggong(DataBean bean) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void zhuceshibai(String message) {
        Toast.makeText(MyActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

    }

    //登录
    public void denglu(View view) {
    }

    public void qqdenglu(View view) {
        UMShareAPI.get(MyActivity.this).getPlatformInfo(MyActivity.this, SHARE_MEDIA.QQ, umAuthListener);

    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            ImageView touxiang= (ImageView) findViewById(R.id.image_view_qq);
            String tu = data.get("iconurl");
            editor.putString("tu",tu);
            editor.commit();
            ImageLoader.getInstance().displayImage(tu,touxiang);
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MyActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
