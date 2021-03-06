package com.lab02.pongoelhombro.View;

import static com.lab02.pongoelhombro.R.drawable.unsacovid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.Presenter.myadapter;
import com.lab02.pongoelhombro.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
     Button primera_dosis;
     View  vista;
     FirebaseFirestore db= FirebaseFirestore.getInstance();
     RecyclerView lista;
     TextView prueba;
    private ArrayList<Noticia> news;
    Button verdosis;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        
        vista=inflater.inflate(R.layout.fragment_home, container, false);


        prueba=vista.findViewById(R.id.notmin);
        lista=vista.findViewById(R.id.news);
        news=new ArrayList<Noticia>();
        db.collection("Noticia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Noticia noticia=new Noticia(document.get("NotImg" )+"",document.get("NotTit" )+"",document.get("NotDes")+"");
                                news.add(noticia);
                                prueba.setText("Noticias actuales");

                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());

                        }
                    }
                });
        lista.setLayoutManager(new LinearLayoutManager(getContext()));
        lista.setAdapter(new myadapter(news));
        return  vista;
    }
}