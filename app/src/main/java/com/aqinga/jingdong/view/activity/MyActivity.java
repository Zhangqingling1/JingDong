package com.aqinga.jingdong.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.bean.DbBean;
import com.aqinga.jingdong.model.utils.DBDao;
import com.aqinga.jingdong.presenter.ZCandDLpresenter;
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

public class MyActivity extends AppCompatActivity{

    private ZCandDLpresenter zCandDLpresenter;
    private SharedPreferences.Editor editor;
    private DBDao dao;
    private EditText name;
    private EditText password;
    private String name1;
    private String password1;
    private DbBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        dao = new DBDao(this);
        name = (EditText) findViewById(R.id.edit_text1);
        password = (EditText) findViewById(R.id.edit_text2);
        findViewById(R.id.edit_text2);
//        zCandDLpresenter = new ZCandDLpresenter(this);
        SharedPreferences sp = getSharedPreferences("key",MODE_PRIVATE);
        editor = sp.edit();
    }

    //手机快速注册
    public void zhuce(View view) {
        Intent intent = new Intent(MyActivity.this,ZhuCeActivity.class);
        startActivity(intent);
    }

//    @Override
//    public Context context() {
//        return this;
//    }
//
//    @Override
//    public void dengluchenggong(DataBean bean) {
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MyActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//    @Override
//    public void denglushibai(String message) {
//        Toast.makeText(MyActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
//
//    }

    //登录
    public void denglu(View view) {
//        zCandDLpresenter.denglu();
        name1 = name.getText().toString();
        password1 = password.getText().toString();
        bean = dao.query(name1, password1);
        if (name1.equals("")||password1.equals("")){
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }else if (name1.equals(bean.getName())&&password1.equals(bean.getPassword())){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        }else {
                Toast.makeText(this, "登录失败,请注册", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyActivity.this,ZhuCeActivity.class);
                startActivity(intent);

            }
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
//            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
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
