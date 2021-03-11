package com.example.futmarket.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ManagerJoueur {
    private List<Joueur> lesJoueurs;
    public MarchePack.OnMarketGeneratedListener listener;


    public void getJoueurFromDatabase(){
        DatabaseReference reference = new Database().firebaseConnexion();


        Task<DataSnapshot> task = reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    GenericTypeIndicator<List<Joueur>> t = new GenericTypeIndicator<List<Joueur>>() {
                    };
                    lesJoueurs = task.getResult().getValue(t);
                    listener.onPackGenerated();
                }
            }
        });

    }

    public List<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }
}

