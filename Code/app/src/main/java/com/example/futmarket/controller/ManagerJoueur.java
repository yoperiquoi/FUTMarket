package com.example.futmarket.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.futmarket.model.Joueur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.List;

/**
 * Classe permettant d'assurer la récupération des joueurs
 */
public class ManagerJoueur {
    private List<Joueur> lesJoueurs;
    public MarchePack.OnMarketGeneratedListener listener;


    /**
     * Permet de récupérer les joueurs depuis la BDD
     */
    public void getJoueurFromDatabase(){
        DatabaseReference reference = new Database().getRef("Joueurs");


        Task<DataSnapshot> task = reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() { // on recupere les joueurs de la bdd
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

    /**
     * Permet de récupérer les joueurs
     * @return les joueurs
     */
    public List<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }
}

