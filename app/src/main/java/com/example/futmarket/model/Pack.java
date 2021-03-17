package com.example.futmarket.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Classe définissant un pack
 */
public class Pack implements Serializable {
    private String name;
    private Rarete rarete;
    private Float prix;
    private List<Joueur> joueurs;
    private String description;
    private int nbJoueurs;

    public Pack(String name, Rarete rarete, Float prix, List<Joueur> joueurs,String description) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.joueurs = joueurs;
        this.description=description;
    }

    public Pack(String name, Rarete rarete, Float prix, String description,int nbJoueurs) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.description = description;
        this.nbJoueurs=nbJoueurs;
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

    /**
     * Permet de générer des joueurs aléatoire à partir de la liste de tout les joueurs
     * @param joueurs tout les joueurs
     */
    public void generatePack(List<Joueur> joueurs){
        Random random = new Random();
        this.joueurs= new LinkedList<>();
        for (int i=0; i < nbJoueurs;i++){
            this.joueurs.add(joueurs.get(random.nextInt(17126)));
        }
    }
}
