package com.lab02.pongoelhombro.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lab02.pongoelhombro.R;


public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    InfoFragment infoFragment = new InfoFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    NewsFragment newsFragment = new NewsFragment();

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new datafragment()).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setItemIconTintList(null);

        ImageView settingButton = (ImageView)findViewById(R.id.settingButton);
        ImageView profileButton = (ImageView)findViewById(R.id.profileButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Intent intent=new Intent(profileButton.getContext(),MainActivity2.class);
                if (user != null) {
                    // User is signed in
                    intent.putExtra("fragment",1);
                } else {
                    // No user is signed in
                    intent.putExtra("fragment",6);
                }

                startActivity(intent);
            }
        });
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