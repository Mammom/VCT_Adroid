package com.daelim.vct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.daelim.vct.fragment.FragHome;
import com.daelim.vct.fragment.FragWallet;
import com.daelim.vct.fragment.FragNews;
import com.daelim.vct.fragment.FragUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment fragment_home;
    Fragment fragment_news;
    Fragment fragment_wallet;
    Fragment fragment_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_home = new FragHome();
        fragment_news = new FragNews();
        fragment_wallet = new FragWallet();
        fragment_user = new FragUser();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_home).commitAllowingStateLoss();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_home).commitAllowingStateLoss();
                        return true;
                    case R.id.news:
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_news).commitAllowingStateLoss();
                        return true;
                    case R.id.wallet:
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_wallet).commitAllowingStateLoss();
                        return true;
                    case R.id.user:
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,fragment_user).commitAllowingStateLoss();
                        return true;
                }
                return true;
            }
        });

    }
}