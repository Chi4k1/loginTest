package com.chi4ki.logintest.mode2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chi4ki.logintest.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class mode2Activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    final String[] titleArray = new String[]{"主页", "我的"};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode2);
        Intent intent = getIntent();
        Log.d("tag","mode2:"+intent.getStringExtra("token"));
    }

}
