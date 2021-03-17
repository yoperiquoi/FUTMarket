package com.example.futmarket.model;

import java.util.LinkedList;
import java.util.List;

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
        retour.addPack("Pack or rare",Rarete.Or,7500f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack légendaire",Rarete.Legende,1000000f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack argent",Rarete.Argent,2500f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack bronze",Rarete.Bronze,1000f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack or",Rarete.Or,5000f,joueurs,"Contient 12 joueurs or");
        return  retour;
    }

}
