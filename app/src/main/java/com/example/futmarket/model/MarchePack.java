package com.example.futmarket.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MarchePack implements Serializable {

    private List<Pack> lesPacks = new ArrayList<>();

    public List<Pack> getLesPacks(){
        return  Collections.unmodifiableList(lesPacks);
    }

    public void addPack(String name, Rarete rarete, Float prix,List<Joueur> joueurs,String description){
        lesPacks.add(new Pack(name,rarete,prix,joueurs,description));
    }

    public MarchePack generatePacks(){
        DatabaseReference reference = new Database().firebaseConnexion();
        Random random = new Random();


        Task<DataSnapshot> t = reference.child("9099").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        return null;
    }
}
