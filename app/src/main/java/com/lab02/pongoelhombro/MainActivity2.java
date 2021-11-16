package com.lab02.pongoelhombro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab02.pongoelhombro.View.*;


public class MainActivity2 extends AppCompatActivity {

    LoginFragment login = new LoginFragment();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle parametro=this.getIntent().getExtras();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new datafragment()).commit();
        //loadFragment(login);
        int fragment=this.getIntent().getIntExtra("fragment",0);
        if(fragment==1)
        {
            loadFragment(login);
        }

    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}