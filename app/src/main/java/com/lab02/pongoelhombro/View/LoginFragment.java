package com.lab02.pongoelhombro.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lab02.pongoelhombro.Presenter.PresenterLogin;
import com.lab02.pongoelhombro.R;
import com.lab02.pongoelhombro.Model.mySharePreference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText user,dni;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    CheckBox recordar;
    Button ingresar;
    mySharePreference pre;
    Button sesion;

    private FirebaseAuth mAuth;
    private PresenterLogin registerAdapter;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        registerAdapter = new PresenterLogin(this.getContext(), mAuth, databaseReference);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_login, container, false);
        inicializar();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Is loged: Activate button "Cerrar Sesion"
            sesion.setText("Cerrar Sesión");
        } else {
            // No user is signed in: Nothing to do
            sesion.setText("Continuar sin iniciar sesión");
        }

        if(pre.revisarSesion())
        {
            user.setText(pre.preferences.getString("user",""));
            dni.setText(pre.preferences.getString("dni",""));
        }else
        {
            //vista.Toast.makeText(this, "Iniciar Sesion", Toast.LENGTH_SHORT).show();
        }
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String UsuNom = user.getText().toString();
                String UsuDni = dni.getText().toString();

                pre.guardarSesion(recordar.isChecked(),UsuNom,UsuDni);

                registerAdapter.signInUser(UsuNom,UsuDni);
            }
        });

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
                    // Is loged:
                    registerAdapter.signOut();

                } else {
                    // No user is signed in:
                    Intent intent1=new Intent(vista.getContext(), MainActivity2.class);
                    intent1.putExtra("fragment",6);
                    startActivity(intent1);
                }
            }
        });


        return vista;
    }

    private void inicializar(){
        pre= new mySharePreference(getActivity().getPreferences(Context.MODE_PRIVATE));
        recordar=vista.findViewById(R.id.recordar);
        user=vista.findViewById(R.id.nombre);
        dni=vista.findViewById(R.id.dni);
        ingresar=vista.findViewById(R.id.ingresar);
        sesion = vista.findViewById(R.id.button2);
    }

}