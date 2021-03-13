package com.example.futmarket.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class Database {
    FirebaseDatabase database;
    Authentification auth = new Authentification();

    public Database(){
        database= FirebaseDatabase.getInstance();
    }

    public void AjoutLogin(String login){
        DatabaseReference userId=database.getReference("Users").child(auth.getCurrentUser().getUid());
        userId.child("login").setValue(login);

    }
    public DatabaseReference getRef(String s){
        return database.getReference(s);
    }
    public void ajouterJoueur(Object obj){

        DatabaseReference userId=database.getReference("Users").child(auth.getCurrentUser().getUid());
        userId.child("joueurs").push().setValue(obj);

    }

}
