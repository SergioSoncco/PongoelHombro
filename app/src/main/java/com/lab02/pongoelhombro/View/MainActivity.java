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
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lab02.pongoelhombro.Dialog;
import com.lab02.pongoelhombro.Dialog_local;
import com.lab02.pongoelhombro.Presenter.Dialog_ver;
import com.lab02.pongoelhombro.R;


public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    InfoFragment infoFragment = new InfoFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    NewsFragment newsFragment = new NewsFragment();

    Button click;
    ImageView profileButton ;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setItemIconTintList(null);

        ImageView settingButton = (ImageView)findViewById(R.id.settingButton);
        ImageView profileButton = (ImageView)findViewById(R.id.profileButton);


        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(profileButton.getContext(),MainActivity2.class);
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
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

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.primera_dosis:
                OpenDialog1 ();
                break;

            case R.id.segunda_dosis:
                OpenDialog2 ();
                break;

        }
    }


    public void OpenDialog1 (){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(),"hola mundo");
    }

    public void OpenDialog2 (){
        Dialog_ver dialog = new Dialog_ver();
        dialog.show(getSupportFragmentManager(),"ver");
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}