package com.aqinga.jingdong.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.aqinga.jingdong.R;
import com.aqinga.jingdong.view.fragment.Classify_Fragment;
import com.aqinga.jingdong.view.fragment.Discover_Fragment;
import com.aqinga.jingdong.view.fragment.Home_Page_Fragment;
import com.aqinga.jingdong.view.fragment.Mine_Fragment;
import com.aqinga.jingdong.view.fragment.Shopping_cartFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup group;
    private FrameLayout fragmentlayout;
    private Button shouye;
    private Button fenlei;
    private Button faxian;
    private Button gouwuche;
    private Button wode;
    private Home_Page_Fragment myFragment;
    private Classify_Fragment myFragment1;
    private Discover_Fragment myFragment2;
    private Shopping_cartFragment shoppingcartFragment;
    private Mine_Fragment myFragment4;
    private ArrayList<Fragment> list;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       init();
    }
    //初始化数据
    public void init(){
        fragmentlayout = (FrameLayout) findViewById(R.id.fragment_layout);
        group = (RadioGroup) findViewById(R.id.group);
        shouye = (Button) findViewById(R.id.shouye);
        fenlei = (Button) findViewById(R.id.fenlei);
        faxian = (Button) findViewById(R.id.faxian);
        gouwuche = (Button)  findViewById(R.id.gouwuche);
        wode = (Button) findViewById(R.id.wode);

        myFragment = new Home_Page_Fragment();
        myFragment1 = new Classify_Fragment();
        myFragment2 = new Discover_Fragment();
        shoppingcartFragment = new Shopping_cartFragment();
        myFragment4 = new Mine_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,myFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,myFragment1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,myFragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, shoppingcartFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,myFragment4).commit();
        group.check(R.id.shouye);
        group.setOnCheckedChangeListener(this);
        getSupportFragmentManager().beginTransaction().show(myFragment).hide(myFragment1)
                .hide(myFragment2).hide(shoppingcartFragment).hide(myFragment4).commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.shouye:
               getSupportFragmentManager().beginTransaction().show(myFragment).hide(myFragment1)
                       .hide(myFragment2).hide(shoppingcartFragment).hide(myFragment4).commit();
                break;
            case R.id.fenlei:
                getSupportFragmentManager().beginTransaction().show(myFragment1).hide(myFragment)
                        .hide(myFragment2).hide(shoppingcartFragment).hide(myFragment4).commit();
                break;
            case R.id.faxian:
                getSupportFragmentManager().beginTransaction().show(myFragment2).hide(myFragment1)
                        .hide(myFragment).hide(shoppingcartFragment).hide(myFragment4).commit();
                break;
            case R.id.gouwuche:
                getSupportFragmentManager().beginTransaction().show(shoppingcartFragment).hide(myFragment1)
                        .hide(myFragment2).hide(myFragment4).hide(myFragment).commit();
            break;
            case R.id.wode:
                getSupportFragmentManager().beginTransaction().show(myFragment4).hide(myFragment)
                        .hide(myFragment1).hide(myFragment2).hide(shoppingcartFragment).commit();
                break;
        }
    }
}
