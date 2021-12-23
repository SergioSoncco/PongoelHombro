package com.lab02.pongoelhombro.Presenter;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.R;
import com.lab02.pongoelhombro.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class Dialog_ver extends AppCompatDialogFragment {
    DbHelper miBD;
    public FirebaseFirestore db;
    public FirebaseAuth mAuth;

    TextView tv;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        miBD = new DbHelper(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.ver,null);

        db = FirebaseFirestore.getInstance();





        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        builder.setView(view)
                .setTitle("Mis Dosis");

        tv= view.findViewById(R.id.textdosis);


        if (currentUser != null) {


            db.collection("Dosis")
                    .whereEqualTo("userId", currentUser.getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {

                                String dos = snapshot.getString("number");
                                String fech = snapshot.getString("date");
                                String vac = snapshot.getString("vacc");

                                String a = new String("hola");
                                tv.append("\nDosis: " + dos +
                                        "\nVacuna: " + vac +
                                        "\nFecha: " + fech + "\n");

                            }
                        }
                    });

        } else {

            List<String> lista = new ArrayList<String>();
            Cursor data = miBD.getListaContenidos();
            while(data.moveToNext()){
                lista.add("\nDosis: " + data.getString(1) +
                        "\nVacuna: " + data.getString(2) +
                        "\nFecha: " + data.getString(3) );
            }
            //    Log.d("fox", listados.toArray(new String[0])[0].toString() );
            builder.setItems(lista.toArray(new String[0]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }

        return builder.create();
    }

}