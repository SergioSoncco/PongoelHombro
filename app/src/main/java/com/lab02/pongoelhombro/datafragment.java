package com.lab02.pongoelhombro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link datafragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class datafragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<com.lab02.pongoelhombro.datamodel> dataholder;

    public datafragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static datafragment newInstance(String param1, String param2) {
        datafragment fragment = new datafragment();
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
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<>();

        com.lab02.pongoelhombro.datamodel ob1=new com.lab02.pongoelhombro.datamodel(R.drawable.unsacovid,"Noticia 4 vacunados","Se vacunaron 4 personas mas");
        dataholder.add(ob1);

        com.lab02.pongoelhombro.datamodel ob2=new com.lab02.pongoelhombro.datamodel(R.drawable.unsacovid,"Noticia 0 fallecidos","Hoy no hubieron fallecidos");
        dataholder.add(ob2);

        com.lab02.pongoelhombro.datamodel ob3=new com.lab02.pongoelhombro.datamodel(R.drawable.unsacovid,"Noticia nuevo local","Nuevo local implementado");
        dataholder.add(ob3);
        com.lab02.pongoelhombro.datamodel ob4=new com.lab02.pongoelhombro.datamodel(R.drawable.unsacovid,"Noticia nuevo local","Nuevo local implementado");
        dataholder.add(ob4);


        recyclerView.setAdapter(new myadapter(dataholder));

        return view;
    }
}