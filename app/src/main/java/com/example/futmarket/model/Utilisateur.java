package com.example.futmarket.model;

import java.util.HashMap;
import java.util.List;

/**
 * Classe définissant un utilisateur de l'application
 */
public class Utilisateur {
    private String login;
    private int credit;
    private HashMap<String,Joueur> joueurs;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public HashMap<String,Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(HashMap<String,Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}
