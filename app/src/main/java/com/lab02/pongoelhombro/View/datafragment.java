package com.lab02.pongoelhombro.View;

import static com.lab02.pongoelhombro.R.drawable.unsacovid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.R;
import com.lab02.pongoelhombro.Presenter.myadapter;

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
    ArrayList<Noticia> dataholder;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    TextView prueba;
    View view;
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
        view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.news);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<Noticia>();
        prueba=view.findViewById(R.id.notmin);



        db.collection("Noticia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Noticia noticia=new Noticia(unsacovid,document.get("NotTit" )+"",document.get("NotDes")+"");
                                dataholder.add(noticia);
                                prueba.setText(document.get("NotTit" )+"");

                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());

                        }
                    }
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new myadapter(dataholder));

        return view;
    }
}