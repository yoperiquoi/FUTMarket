package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futmarket.R;
import com.example.futmarket.model.Database;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.model.Pack;
import com.example.futmarket.model.Utilisateur;
import com.example.futmarket.view.adaptateur.AdaptateurJoueur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class OuverturePack extends AppCompatActivity {
    private Pack pack;
    private LinkedList<Joueur> joueurs;
    private Database db;
    private Utilisateur user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ouverture_pack);
        db = new Database();
        user = db.getUser();

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

        db.listener = new Database.OnUserLoaded() {
            @Override
            public void Userloaded() {
                    //db.getRef("Users").child(db.getUserId()).child("credit").setValue(user.getCredit() - Integer.parseInt(pack.getPrix()));
            }
        };
        db.fetchUser();

        joueurs= (LinkedList<Joueur>) pack.getJoueurs();
        RecyclerView laListView = findViewById(R.id.listView2);
        int valeurJoueur=0;

        for (Joueur joueur : joueurs){
            db.ajouterJoueur(joueur);
            valeurJoueur+=joueur.getPrix();
        }
        ((TextView)findViewById(R.id.ValeurPack)).setText(getString(R.string.valeurPack)+ valeurJoueur +getString(R.string.euro));

        laListView.setLayoutManager(new LinearLayoutManager(this));
        laListView.setAdapter(new AdaptateurJoueur(joueurs,this.getApplicationContext()));
        Log.d("toto", "onCreate: ");

        retour();
    }



    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null)
                .commit();
    }

    public void retour() {
        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener( v-> {
            startActivity(new Intent(getApplicationContext(),ActiviteMode.class));
            finish();
        });
    }


}