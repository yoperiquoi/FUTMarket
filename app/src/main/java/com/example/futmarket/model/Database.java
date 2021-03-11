package com.example.futmarket.model;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    public DatabaseReference firebaseConnexion(){
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        return database.getReference("Joueurs");
    }

}
