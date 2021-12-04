package com.lab02.pongoelhombro.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab02.pongoelhombro.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsVacunaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsVacunaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View vista;
    TextView titulo,t1,t2,t3;
    Button btn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsVacunaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsVacunaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsVacunaFragment newInstance(String param1, String param2) {
        DetailsVacunaFragment fragment = new DetailsVacunaFragment();
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
        vista= inflater.inflate(R.layout.fragment_details_vacuna, container, false);
        titulo= vista.findViewById(R.id.tvvacunaname);
        t1=vista.findViewById(R.id.ptv1);
        t2=vista.findViewById(R.id.ptv2);
        t3=vista.findViewById(R.id.ptv3);
        Bundle datos=getArguments();
        if(datos!=null)
        {
            String titul=datos.getString("Titulo");
            String st1= datos.getString("vacPai");
            String st2=datos.getString("VacLab");
            String st3= datos.getString("VacSin");

            titulo.setText(titul);
            t1.setText(st1);
            t2.setText(st2);
            t3.setText(st3);

        }
        btn = vista.findViewById(R.id.bshare);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = titulo.getText()+"\n Pais:"+t1.getText()+"\n Laboratorio: "+t2.getText()+"\n Sintomas: "+t3.getText();
                String Sub = titulo.getText()+"\n Pais:"+t1.getText()+"\n Laboratorio: "+t2.getText()+"\n Sintomas: "+t3.getText();
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Compartir en"));

            }
        });
        return vista;
    }
}