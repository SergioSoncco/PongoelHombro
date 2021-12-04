package com.lab02.pongoelhombro.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.Model.Vacuna;
import com.lab02.pongoelhombro.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AstraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AstraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView t1,t2,t3;
    Button btn;
    View vista;
    DatabaseReference reff;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    private ArrayList<Vacuna> vacs;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AstraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AstraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AstraFragment newInstance(String param1, String param2) {
        AstraFragment fragment = new AstraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_astra, container, false);
        t1= vista.findViewById(R.id.ptv1);
        t2= vista.findViewById(R.id.ptv2);
        t3= vista.findViewById(R.id.ptv3);
        String pais="",lab,sint;
        vacs=new ArrayList<Vacuna>();
        db.collection("Vacuna")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if("AstraZeneca".equals(document.get("VacNom")+"")) {
                                    Vacuna vacunad = new Vacuna(document.get("VacPai") + "", document.get("VacLab") + "", document.get("VacSin") + "");
                                    t1.setText(vacunad.getVacPai());
                                    t2.setText(vacunad.getVacLab());
                                    t3.setText(vacunad.getVacSin());
                                }

                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());

                        }
                    }
                });

        btn = vista.findViewById(R.id.bshare);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "AstraZeneca, Pais:"+t1.getText()+"\n Laboratorio: "+t2.getText()+"\n Sintomas: "+t3.getText();
                String Sub = "AstraZeneca, Pais:"+t1.getText()+"\n Laboratorio: "+t2.getText()+"\n Sintomas: "+t3.getText();
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Compartir en"));

            }
        });

        return vista;
    }
}