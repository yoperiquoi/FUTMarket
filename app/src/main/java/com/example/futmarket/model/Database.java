package com.example.futmarket.model;

import android.util.Log;
import android.widget.Adapter;

import androidx.annotation.NonNull;

import com.example.futmarket.view.adaptateur.AdaptateurJoueur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class Database {
    FirebaseDatabase database;
    Authentification auth = new Authentification();

    public Database(){
        database= FirebaseDatabase.getInstance();
    }

    public void AjouterGoogle(String login){
        DatabaseReference userId=getRef("Users/"+getUserId());
        userId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(login.equals(snapshot.child("login").getValue())){
                    userId.child("login").setValue(login);
                }
                else{
                    userId.child("login").setValue(login);
                    userId.child("credit").setValue(1000000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void AjoutUser(String login){
        DatabaseReference userId=database.getReference("Users").child(getUserId());
        userId.child("credit").setValue(1000000);
        userId.child("login").setValue(login);

    }
    public DatabaseReference getRef(String s){
        return database.getReference(s);
    }

    public String getUserId(){
        return auth.getCurrentUser().getUid();
    }

    public void ajouterJoueur(Object obj){
        getRef("Users/"+getUserId()+"/joueurs").push().setValue(obj);
    }

    public String getUserCredits(){
        return getRef("Users/"+getUserId()+"/credit").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String.valueOf(task.getResult().getValue());
            }
        }).toString();
    }

    public void getJoueurs(DatabaseReference ref, AdaptateurJoueur adapter , LinkedList<Joueur> list){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Joueur joueur = dataSnapshot.getValue(Joueur.class);
                    list.add(joueur);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
