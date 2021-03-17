package com.example.futmarket.model;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe définisant le marché des packs
 */
public class MarchePack implements Serializable {
    private List<Pack> lesPacks = new ArrayList<>();
    public OnMarketGeneratedListener listener;

    /**
     * Méthode permettant d'ajouter un pack dans le marché
     * @param name nom du pack
     * @param rarete rarete
     * @param prix prix
     * @param joueurs joueurs présent dans le pack
     * @param description description du pack
     */
    public void addPack(String name, Rarete rarete, Float prix,List<Joueur> joueurs,String description){
        lesPacks.add(new Pack(name,rarete,prix,joueurs,description));
    }

    /**
     * Permet de générer les packs
     */
    public void generatePacks(){
        DatabaseReference reference = new Database().getRef("Joueurs");
        Task<DataSnapshot> task = reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    GenericTypeIndicator<List<Joueur>> t = new GenericTypeIndicator<List<Joueur>>() {};
                    List<Joueur> var = task.getResult().getValue(t);
                    Pack pack1 = new Pack("Pack or",Rarete.Or,7500f,"Contient 3 joueurs",3);
                    Pack pack2 = new Pack("Pack légendaire",Rarete.Legende,1000000f,"Contient 5 joueurs",5);
                    Pack pack3 = new Pack("Pack argent",Rarete.Argent,2500f,"Contient 2 joueurs ",2);
                    Pack pack4 = new Pack("Pack bronze",Rarete.Bronze,1000f,"Contient 1 joueur",1);
                    pack1.generatePack(var);
                    pack2.generatePack(var);
                    pack3.generatePack(var);
                    pack4.generatePack(var);
                    lesPacks = new LinkedList<>();
                    lesPacks.add(pack1);
                    lesPacks.add(pack2);
                    lesPacks.add(pack3);
                    lesPacks.add(pack4);
                    listener.onPackGenerated();
                }
            }
        });
    }

    /**
     * Permet d'assurer que les packs sont chargé
     */
    public interface OnMarketGeneratedListener {
        void onPackGenerated();
    }

    public List<Pack> getLesPacks(){
        return  Collections.unmodifiableList(lesPacks);
    }
}