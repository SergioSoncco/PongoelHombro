package com.lab02.pongoelhombro.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.WindowManager;

import com.lab02.pongoelhombro.R;


public class MainActivity2 extends AppCompatActivity {

    LoginFragment login = new LoginFragment();
    PrimeraDosisFragment primera_dosis=new PrimeraDosisFragment();
    SegundaDosisFragment segunda_dosis=new SegundaDosisFragment();
    RegisterFragment registerFragment=new RegisterFragment();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle parametro=this.getIntent().getExtras();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();
        //loadFragment(login);



        int fragment=this.getIntent().getIntExtra("fragment",0);
        if(fragment==1)
        {
            loadFragment(login);
        }
        if(fragment==4)
        {
            loadFragment(primera_dosis);
        }
        if(fragment==5)
        {
            loadFragment(segunda_dosis);
        }
        if(fragment==6)
        {
            loadFragment(registerFragment);
        }


    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}