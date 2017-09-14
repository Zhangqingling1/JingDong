package com.aqinga.jingdong.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.model.utils.DBDao;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/139:28
 */

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name1;
    private EditText password1;
    private Button button;
    private DBDao dao;
    private String name;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuceactivity);
        dao = new DBDao(this);
        name1 = (EditText) findViewById(R.id.edit_text1_zhuce);
        password1 = (EditText) findViewById(R.id.edit_text2_zhuce);
        button = (Button) findViewById(R.id.but_zhuce);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        name = name1.getText().toString().trim();
        password = password1.getText().toString().trim();
        if (name.equals("")||password.equals("")){
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }else{
           dao.add(name,password);
            Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
