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
    private int prix;
    private List<Joueur> joueurs;
    private String description;
    private int nbJoueurs;

    /**
     * initialisation d'un pack
     * @param name du pack
     * @param rarete du pack
     * @param prix du pack
     * @param joueurs du pack
     * @param description du pack
     */
    public Pack(String name, Rarete rarete, int prix, List<Joueur> joueurs,String description) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.joueurs = joueurs;
        this.description=description;
    }

    /**
     * initialisation d'un pack
     * @param name du pack
     * @param rarete du pack
     * @param prix du pack
     * @param description du pack
     * @param nbJoueurs dans le pack
     */
    public Pack(String name, Rarete rarete, int prix, String description,int nbJoueurs) {
        this.name = name;
        this.rarete = rarete;
        this.prix = prix;
        this.description = description;
        this.nbJoueurs=nbJoueurs;
    }

    /**
     * getteur du nom du pack
     * @return nom du pack
     */
    public String getName() {
        return name;
    }

    /**
     * setteur du nom d'un pack
     * @param name d'un pack
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getteur du prix d'un pack en string
     * @return prix du pack
     */
    public String getPrix() {
        return Integer.toString(prix);
    }

    /**
     * getteur du prix de pack
     * @return prix du pack en int
     */
    public int getPrixPack(){
        return prix;
    }

    /**
     * getteur des joueurs dans le pack
     * @return joueurs
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * setteur des joueurs dans le pack
     * @param joueurs a mettre dans le pack
     */
    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * getteur de description d'un pack
     * @return la description
     */
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
