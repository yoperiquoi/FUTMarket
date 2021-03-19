package com.example.futmarket.model;

import com.example.futmarket.controller.MarchePack;

import java.util.LinkedList;

/**
 * Stub permettant les test du métier en créant des données fictive
 */
public class Stub {

    /**
     * Permet de charger un marché avec des données fictives
     * @return un marche de pack
     */
    public static MarchePack loadMarche(){
        MarchePack retour = new MarchePack();
        LinkedList<Joueur> joueurs= new LinkedList<>();
        joueurs.add(new Joueur());
        retour.addPack("Pack or rare",Rarete.Or,7500,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack légendaire",Rarete.Legende,1000000,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack argent",Rarete.Argent,2500,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack bronze",Rarete.Bronze,1000,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack or",Rarete.Or,5000,joueurs,"Contient 12 joueurs or");
        return  retour;
    }

}
