package com.lab02.pongoelhombro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lab02.pongoelhombro.Model.mySharePreference;
import com.lab02.pongoelhombro.Presenter.PresenterDosis;
import com.lab02.pongoelhombro.db.DbHelper;

public class Dialog extends AppCompatDialogFragment {

    private Spinner Dosis,Vacuna;
    private EditText Fecha;
    DbHelper miBD;

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    private PresenterDosis presenterDosis;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        miBD = new DbHelper(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.formulario,null);

        db = FirebaseFirestore.getInstance();
        presenterDosis = new PresenterDosis(this.getContext(),db);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        builder.setView(view)
                .setTitle("Registrar Dosis")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialoginterface, int i){

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String select_dosis = Dosis.getSelectedItem().toString();
                        String select_vacuna = Vacuna.getSelectedItem().toString();
                        String select_fecha = Fecha.getText().toString();

                        if (currentUser != null) {
                            presenterDosis.saveDosis(select_fecha,select_vacuna,select_dosis,currentUser.getUid());
                        }else{
                            miBD.addData(select_dosis,select_vacuna,select_fecha);
                        }

                    }
                });


        Dosis = view.findViewById(R.id.spin_dosis);
        String[] menu = { "Primera Dosis","Segunda Dosis", "Tercera Dosis"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, menu);
        Dosis.setAdapter(adapter);

        Fecha = view.findViewById(R.id.fecha);

        Vacuna = view.findViewById(R.id.spin_vacuna);
        String[] menu2 = { "Pfizer","Sinopharm", "AstraZeneca"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, menu2);
        Vacuna.setAdapter(adapter2);


        return builder.create();

    }


}
