package com.example.futmarket.model;

import android.util.Log;

import java.util.List;

public class Utilisateur {
    private String pseudo;
    private String credits;
    private List<Joueur> mesJoueurs;
    private Database db = new Database();
    private Authentification auth = new Authentification();

    public Utilisateur(){
       // this.pseudo=auth.getName();
       // this.credits= db.getUserCredits().toString();
    }

    public String getCredits() {
        return credits;
    }



    public String getPseudo() {
        return pseudo;
    }
}
