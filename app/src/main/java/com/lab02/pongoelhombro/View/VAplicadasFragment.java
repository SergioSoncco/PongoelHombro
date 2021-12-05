package com.lab02.pongoelhombro.View;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VAplicadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VAplicadasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    FirebaseFirestore db2= FirebaseFirestore.getInstance();
    RecyclerView recycler;
    TextView prueba;
    WebView webView;

    private ArrayList<Vacuna> vacunas;
    public VAplicadasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VAplicadasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VAplicadasFragment newInstance(String param1, String param2) {
        VAplicadasFragment fragment = new VAplicadasFragment();
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
        vista= inflater.inflate(R.layout.fragment_v_aplicadas, container, false);
        webView= vista.findViewById(R.id.webview);
        webView.loadUrl("https://www.minsa.gob.pe/reunis/data/vacunas-covid19.asp");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return vista;
    }
}