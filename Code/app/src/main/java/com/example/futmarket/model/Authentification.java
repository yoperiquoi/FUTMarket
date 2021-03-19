package com.example.futmarket.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Classe responsable de l'authentification de l'utilisateur
 */
public class Authentification {
    /**
     * Outils d'authentification de Firebase
     */
    private final FirebaseAuth mAuth;

    /**
     * Constructeur récupérant l'instance d'authentification
     */
    public Authentification(){
        mAuth= FirebaseAuth.getInstance();
    }

    /**
     * Permet de récupérer l'utilisateur courrant
     * @return utilisateur courant
     */
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    /**
     * Déconnecte l'utilisateur
     */
    public void deconnect(){
        mAuth.signOut();
    }


    /**
     * Permet de savoir si l'utilisateur est connecté
     * @return status de connection
     */
    public boolean isConnected(){ return getCurrentUser() != null; }

    /**
     * Permet de connaître le nom de l'utilisateur
     * @return nom de l'utilisateur
     */
    public String getName(){
        return getCurrentUser().getDisplayName();
    }

}
