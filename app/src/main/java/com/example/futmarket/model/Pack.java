package com.example.futmarket.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Pack implements Serializable {
    private String name;
    private Rarete rarete;
    private Float prix;
    private List<Joueur> joueurs;
    private String description;

    public Pack(String name, Rarete rarete, Float prix, List<Joueur> joueurs,String description) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.joueurs = joueurs;
        this.description=description;
    }

    public Pack(String name, Rarete rarete, Float prix, String description) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarete getRarete() {
        return rarete;
    }

    public void setRarete(Rarete rarete) {
        this.rarete = rarete;
    }

    public String getPrix() {
        return prix.toString();
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public String getDescription() {
        return description;
    }

    public void generateJoueur(){
        LinkedList<Joueur> newList = new LinkedList<Joueur>();


    }
}
