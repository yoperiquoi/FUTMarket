package com.example.futmarket.model;

import android.app.job.JobServiceEngine;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MarchePack implements Serializable {

    private List<Pack> lesPacks = new ArrayList<>();
    public OnMarketGeneratedListener listener;

    public List<Pack> getLesPacks(){
        return  Collections.unmodifiableList(lesPacks);
    }

    public void addPack(String name, Rarete rarete, Float prix,List<Joueur> joueurs,String description){
        lesPacks.add(new Pack(name,rarete,prix,joueurs,description));
    }

    public MarchePack generatePacks(){
        DatabaseReference reference = new Database().firebaseConnexion();
        Random random = new Random();


        Task<DataSnapshot> task = reference.child(Integer.toString(random.nextInt(17126))).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Joueur joueur= task.getResult().getValue(Joueur.class);
                    LinkedList<Joueur> joueurs =new LinkedList<Joueur>();
                    joueurs.add(joueur);
                    addPack("Pack légendaire",Rarete.Legende,1000000f,joueurs,"Contient 12 joueurs or");

                    listener.onPackGenerated();
                }
            }
        });
        return null;
    }

    public interface OnMarketGeneratedListener {

        void onPackGenerated();
    }
}
