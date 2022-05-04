package com.chi4ki.logintest.mode3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.chi4ki.logintest.R;
import com.google.android.material.tabs.TabLayout;

public class mode3Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    final String[] titleArray = new String[]{"主页", "我的"};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode3);
        Intent intent = getIntent();
        Log.d("tag","mode3:"+intent.getStringExtra("token"));
    }

}
