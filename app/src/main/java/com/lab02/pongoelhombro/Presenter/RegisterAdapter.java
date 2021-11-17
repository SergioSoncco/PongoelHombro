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

public class RegisterAdapter {

    private Context context;
    private String TAG = "RegisterAdapter";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public RegisterAdapter(Context context, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.context = context;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void signInUser(String UsuNom, String UsuDni){
        mAuth.signInWithEmailAndPassword(UsuNom, UsuDni)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(context, "Inició Sesión",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            mDatabase.child("Usuario").setValue(task.getResult().getUser().getUid());

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
