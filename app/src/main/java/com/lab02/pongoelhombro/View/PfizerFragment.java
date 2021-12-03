package com.lab02.pongoelhombro.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
 * Use the {@link PfizerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PfizerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView t1,t2,t3;
    Button btn;
    View vista;
    DatabaseReference reff;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    private ArrayList<Vacuna> vacs;

    public PfizerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pfizer.
     */
    // TODO: Rename and change types and number of parameters
    public static PfizerFragment newInstance(String param1, String param2) {
        PfizerFragment fragment = new PfizerFragment();
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
        vista= inflater.inflate(R.layout.fragment_pfizer, container, false);
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
                                if("Pfizer".equals(document.get("VacNom")+"")) {
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

        return vista;
    }
}