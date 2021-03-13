package com.example.futmarket.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentification {
    private FirebaseAuth mAuth;
    public Authentification(){
        mAuth= FirebaseAuth.getInstance();
    }

    public void deconnect(){
        mAuth.signOut();
    }

    public boolean isConnected(){
        if(mAuth.getCurrentUser() != null)return true;
        return false;
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

}
