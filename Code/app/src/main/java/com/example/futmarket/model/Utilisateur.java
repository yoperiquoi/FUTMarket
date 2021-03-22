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

    /**
     * return de login de l'utilisateur
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * defini le login de l'utilisateur
     * @param login le login a definir
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * recuperation des credit de l'utilisateur
     * @return le nombre des credits
     */
    public int getCredit() {
        return credit;
    }

    /**
     * defini le nombre des credits
     * @param credit le nombre de credit a defiir
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * recuperation des joueurs de l'utilisateur
     * @return les joueurs
     */
    public HashMap<String,Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * defini les joueurs de l'utilisateur
     * @param joueurs les joueurs
     */
    public void setJoueurs(HashMap<String,Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}
