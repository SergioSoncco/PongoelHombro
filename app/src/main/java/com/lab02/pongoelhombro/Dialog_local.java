package com.lab02.pongoelhombro;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.firebase.firestore.FirebaseFirestore;


public class Dialog_local extends AppCompatDialogFragment {

    private TextView localname;
    private TextView latitud;
    private TextView longitud;
    private Button btn;
    private FirebaseFirestore db;


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.local_info,null);
        db = FirebaseFirestore.getInstance();

        Bundle mArgs = getArguments();

        builder.setView(view);

        btn = view.findViewById(R.id.btncompartir);

        localname = view.findViewById(R.id.localname);
        latitud = view.findViewById(R.id.latitud);
        longitud = view.findViewById(R.id.longitud);

        localname.setText(mArgs.getString("name_"));
        latitud.setText(mArgs.getString("lat_"));
        longitud.setText(mArgs.getString("log_"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Sub = "Lugar: " + mArgs.getString("name_")+ "\n"+
                        "latitud: "+ mArgs.getString("lat_")+ "\n"+
                        "longitud: "+ mArgs.getString("log_");

                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Compartir en"));

            }
        });

        return builder.create();

    }


}