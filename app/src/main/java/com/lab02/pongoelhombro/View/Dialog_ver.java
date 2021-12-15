package com.lab02.pongoelhombro;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.lab02.pongoelhombro.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class Dialog_ver extends AppCompatDialogFragment {
    DbHelper miBD;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        miBD = new DbHelper(getContext());
        List<String> listados = new ArrayList<String>();
        Cursor data = miBD.getListaContenidos();
        while(data.moveToNext()){
            listados.add("\nDosis: " + data.getString(1) +
                    "\nVacuna: " + data.getString(2) +
                    "\nFecha: " + data.getString(3) );
        }
        String[] stringArray = listados.toArray(new String[0]);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Mis Dosis");
        builder.setItems(stringArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();

    }

}