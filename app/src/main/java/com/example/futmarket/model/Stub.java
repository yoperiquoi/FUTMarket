package com.example.futmarket.model;

import java.util.LinkedList;
import java.util.List;

public class Stub {

    public static MarchePack loadMarche(){
        MarchePack retour = new MarchePack();
        LinkedList<Joueur> joueurs= new LinkedList<>();
        joueurs.add(new Joueur("Cristiano Ronaldo",35,"ST","https://cdn.sofifa.com/players/020/801/20_120.png","https://cdn.sofifa.com/teams/45/60.png",185,70,93,100000000,"https://cdn.sofifa.com/flags/pt.png",Rarete.Or));
        retour.addPack("Pack or rare",Rarete.Or,7500f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack légendaire",Rarete.Legende,1000000f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack argent",Rarete.Argent,2500f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack bronze",Rarete.Bronze,1000f,joueurs,"Contient 12 joueurs or");
        retour.addPack("Pack or",Rarete.Or,5000f,joueurs,"Contient 12 joueurs or");
        return  retour;
    }

}
