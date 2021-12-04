package com.lab02.pongoelhombro.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.Model.Vacuna;
import com.lab02.pongoelhombro.Presenter.myadapter2;
import com.lab02.pongoelhombro.R;
import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.Presenter.myadapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam1;
    private String mParam2;
    View  vista;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    RecyclerView recycler;
    TextView prueba;
    private ArrayList<Vacuna> vacunas;
    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_info, container, false);
        prueba=vista.findViewById(R.id.tv2);
        recycler=vista.findViewById(R.id.recvacunas);
        vacunas=new ArrayList<Vacuna>();
        db.collection("Vacuna")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Vacuna newvacuna=new Vacuna(document.get("VacPai")+"",document.get("VacLab" )+"",document.get("VacSin")+"");
                                prueba.setText("Vacunas:");
                                vacunas.add(newvacuna);

                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());

                        }
                    }
                });
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        myadapter2 adapt=new myadapter2(vacunas);
        adapt.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsVacunaFragment New = new DetailsVacunaFragment();
                Bundle datos=new Bundle();

                datos.putString("Titulo", vacunas.get(recycler.getChildAdapterPosition(view)).getVacLab());
                datos.putString("vacPai", vacunas.get(recycler.getChildAdapterPosition(view)).getVacPai());
                datos.putString("VacLab", vacunas.get(recycler.getChildAdapterPosition(view)).getVacLab());
                datos.putString("VacSin", vacunas.get(recycler.getChildAdapterPosition(view)).getVacSin());
                Toast.makeText(getActivity().getApplicationContext(),
                        "Seleccion: "+vacunas.get(recycler.getChildAdapterPosition(view)).getVacLab(),Toast.LENGTH_SHORT).show();

                New.setArguments(datos);
                final FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_container,New);
                ft.addToBackStack("tag");
                ft.commit();
            }
        });

        recycler.setAdapter(adapt);


        return  vista;
    }
}