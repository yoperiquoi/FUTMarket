package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.futmarket.R;
import com.example.futmarket.model.Database;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.model.Pack;
import com.example.futmarket.view.adaptateur.AdaptateurJoueur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class OuverturePack extends AppCompatActivity {
    private Pack pack;
    private LinkedList<Joueur> joueurs;
    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ouverture_pack);

        try{
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(getFilesDir()+"/OuverturePack"));
            pack = (Pack) objectIn.readObject();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        joueurs= (LinkedList<Joueur>) pack.getJoueurs();
        RecyclerView laListView = findViewById(R.id.listView2);


        int valeurJoueur=0;

        for (Joueur joueur : joueurs){
            db.ajouterJoueur(joueur);
            valeurJoueur+=joueur.getPrix();
        }
        ((TextView)findViewById(R.id.ValeurPack)).setText(getString(R.string.valeurPack)+Integer.toString(valeurJoueur)+getString(R.string.euro));

        laListView.setLayoutManager(new LinearLayoutManager(this));
        laListView.setAdapter(new AdaptateurJoueur(joueurs,this.getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null)
                .commit();
    }

}