package com.lab02.pongoelhombro.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lab02.pongoelhombro.Presenter.PresenterDosis;
import com.lab02.pongoelhombro.Presenter.PresenterLogin;
import com.lab02.pongoelhombro.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrimeraDosisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimeraDosisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView fecha;
    TextView vacuna;
    Button ingresar;

    private FirebaseFirestore db;
    private PresenterDosis presenterDosis;


    public PrimeraDosisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DosisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrimeraDosisFragment newInstance(String param1, String param2) {
        PrimeraDosisFragment fragment = new PrimeraDosisFragment();
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
        db = FirebaseFirestore.getInstance();
        presenterDosis = new PresenterDosis(this.getContext(),db);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_primera_dosis, container, false);

        fecha = vista.findViewById(R.id.editTextDate);
        vacuna = vista.findViewById(R.id.editTextTextPersonName2);
        ingresar = vista.findViewById(R.id.ingresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fecha = fecha.getText().toString();
                String Vacuna = vacuna.getText().toString();

                presenterDosis.saveDosis(Fecha,Vacuna,"1");
            }
        });


        return vista;
    }
}