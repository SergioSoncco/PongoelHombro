package com.lab02.pongoelhombro.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PresenterRegister {

    private Context context;
    private String TAG = "PresenterLogin";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public PresenterRegister(Context context, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.context = context;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void signUpUser(String UsuCor, String UsuCon){
        mAuth.createUserWithEmailAndPassword(UsuCor, UsuCon)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUSer:success");
                            Toast.makeText(context, "Creado Exitosamente",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Map<String, Object> usuario = new HashMap<>();
                            usuario.put("UsuCod", UUID.randomUUID().toString());
                            usuario.put("UsuCor",UsuCor);
                            usuario.put("UsuCon",UsuCon);
                            usuario.put("UsuLon","");
                            usuario.put("UsuLat","");
                            mDatabase.child("Usuario").child(task.getResult().getUser().getUid()).updateChildren(usuario);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "No se pudo logear",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
