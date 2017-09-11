package com.aqinga.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.aqinga.jingdong.R;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/79:31
 */

public class WelcomeActivity extends AppCompatActivity {

    private TextView textview;
    private int count = 5;
    private Animation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_activity);
        textview = (TextView) findViewById(R.id.miaoshu);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        handler.sendEmptyMessageDelayed(0, 1500);
    }
    private int getCount(){
        count--;
        if (count==0){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textview.setText(getCount()+"");
            handler.sendEmptyMessageDelayed(0,1500);
            animation.reset();
            textview.startAnimation(animation);
        }
    };

    public void tiaoguo(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //移除handler所有消息队列
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}
