package com.example.futmarket.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarchePack implements Serializable {

    private List<Pack> lesPacks = new ArrayList<>();

    public List<Pack> getLesPacks(){
        return  Collections.unmodifiableList(lesPacks);
    }

    public void addPack(String name, Rarete rarete, Float prix,List<Joueur> joueurs,String description){
        lesPacks.add(new Pack(name,rarete,prix,joueurs,description));
    }
}
