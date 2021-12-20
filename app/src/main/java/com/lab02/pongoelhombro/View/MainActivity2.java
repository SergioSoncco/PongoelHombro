package com.lab02.pongoelhombro.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.Model.Vacuna;
import com.lab02.pongoelhombro.R;


public class MainActivity2 extends AppCompatActivity {

    LoginFragment login = new LoginFragment();
    PrimeraDosisFragment primera_dosis=new PrimeraDosisFragment();
    SegundaDosisFragment segunda_dosis=new SegundaDosisFragment();
    RegisterFragment registerFragment=new RegisterFragment();
    VAplicadasFragment vAplicadasFragment= new VAplicadasFragment();
    DosisFragment dosisFragment= new DosisFragment();
    DetailNewsFragment newFragment = new DetailNewsFragment();
    DetailsVacunaFragment vacdetFragment= new DetailsVacunaFragment();

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
        if(fragment==7)
        {
            loadFragment(vAplicadasFragment);
        }
        if(fragment==8)
        {
            loadFragment(dosisFragment);
        }
        if(fragment==9)
        {
            Noticia noticia= (Noticia) getIntent().getSerializableExtra("Noticia");
            if(noticia!=null)
            {
                Bundle datos=new Bundle();
                datos.putString("Titulo", noticia.getHeader());
                datos.putString("Descripcion", noticia.getDesc());
                datos.putString("Imagen", noticia.getImage());
                newFragment.setArguments(datos);
               loadFragment(newFragment);
            }

            //loadFragment(dosisFragment);
        }
        if(fragment==10)
        {
            Vacuna vac= (Vacuna) getIntent().getSerializableExtra("Vacs");
            if(vac!=null)
            {
                Bundle datos2=new Bundle();

                datos2.putString("Titulo", vac.getVacLab());
                datos2.putString("vacPai", vac.getVacPai());
                datos2.putString("VacLab", vac.getVacLab());
                datos2.putString("VacSin", vac.getVacSin());

                vacdetFragment.setArguments(datos2);
                loadFragment(vacdetFragment);
            }


            //loadFragment(dosisFragment);
        }



    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }


}