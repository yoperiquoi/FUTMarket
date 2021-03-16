package com.example.futmarket.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class Database {
    private FirebaseDatabase database;
    private Authentification auth = new Authentification();
    private Utilisateur user= new Utilisateur();
    public OnUserLoaded listener;
    private int prix;

    public Database(){
        database= FirebaseDatabase.getInstance();
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;

    }

    public void AjoutUser(String login){
        DatabaseReference userId=database.getReference("Users").child(getUserId());
        userId.child("credit").setValue(1000000);
        userId.child("login").setValue(login);

    }

    public void AjouterGoogle(String login){
        DatabaseReference userId=database.getReference("Users").child(getUserId());
        userId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("toto", "onDataChange: "+snapshot.child("login").getValue());
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

    public DatabaseReference getRef(String s){
        return database.getReference(s);
    }
    public String getUserId(){
        return auth.getCurrentUser().getUid();
    }
    public void ajouterJoueur(Object obj){
       getRef("Users").child(getUserId()).child("joueurs").push().setValue(obj);
    }

    public Utilisateur getUser() {
       return user;
    }

    public void fetchUser() {
        Task<DataSnapshot> task = getRef("Users").child(getUserId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    GenericTypeIndicator<Utilisateur> t = new GenericTypeIndicator<Utilisateur>() {
                    };
                    user = task.getResult().getValue(t);
                    listener.Userloaded();
                }
            }
        });
    }

    public interface OnUserLoaded{
        void Userloaded();
    }
}
