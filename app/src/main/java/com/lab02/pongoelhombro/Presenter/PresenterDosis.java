package com.lab02.pongoelhombro.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class PresenterDosis {

    private Context context;
    private String TAG = "PresenterLogin";
    private FirebaseFirestore db;

    public PresenterDosis(Context context, FirebaseFirestore db) {
        this.context = context;
        this.db = db;
    }

    public void saveDosis(String Fecha, String Vacuna, String Dosis, String user){

        Map<String, Object> dosis = new HashMap<>();
        dosis.put("date", Fecha);
        dosis.put("vacc", Vacuna);
        dosis.put("number", Dosis);
        dosis.put("userId", user);


        db.collection("Dosis")
                .add(dosis)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
