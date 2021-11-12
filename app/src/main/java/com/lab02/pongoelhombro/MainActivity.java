package com.lab02.pongoelhombro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab02.pongoelhombro.View.*;


public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    InfoFragment infoFragment = new InfoFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    NewsFragment newsFragment = new NewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setItemIconTintList(null); //added
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.homeFragment:
                    loadFragment(homeFragment);
                    return true;
                case R.id.mapFragment:
                    loadFragment(mapFragment);
                    return true;
                case R.id.infoFragment:
                    loadFragment(infoFragment);
                    return true;
                case R.id.calendarFragment:
                    loadFragment(calendarFragment);
                    return true;
                case R.id.newsFragment:
                    loadFragment(newsFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}