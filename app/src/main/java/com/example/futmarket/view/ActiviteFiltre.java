package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.model.ManagerJoueur;
import com.example.futmarket.model.MarchePack;
import com.example.futmarket.model.Pack;
import com.example.futmarket.view.adaptateur.AdaptateurJoueur;
import com.example.futmarket.view.adaptateur.AdaptateurMarche;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe reponsable de l'affichage du marché de joueur
 */
public class ActiviteFiltre extends AppCompatActivity {
    ManagerJoueur manager;
    List<Joueur> lesJoueurs ;
    private Joueur joueurEnCours;
    private AdaptateurJoueur adapter;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_filtre);

        RecyclerView laListView = findViewById(R.id.listView3);
        progress = findViewById(R.id.progress2);


        laListView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdaptateurJoueur(new LinkedList<>(),getApplicationContext());
        laListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();

        lesJoueurs = new LinkedList<>();

        manager= new ManagerJoueur();
        adapter.refreshData(new LinkedList<>());

        progress.setVisibility(View.VISIBLE);

        manager.listener = new MarchePack.OnMarketGeneratedListener() {
            @Override
            public void onPackGenerated() {
                lesJoueurs = manager.getLesJoueurs();
                adapter.refreshData(lesJoueurs);
                progress.setVisibility(View.GONE);
            }
        };
        manager.getJoueurFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Retourne le joueur en cours
     * @return joueur en cours
     */
    public Joueur getJoueurEnCours() {
        return joueurEnCours;
    }

    /**
     * Défini le joueur en cours
     * @param joueurEnCours joueur en cours
     */
    public void setJoueurEnCours(Joueur joueurEnCours) {
        if(this.joueurEnCours != joueurEnCours){
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack("master")
                    .replace(R.id.listView3,DetailsJoueurAchat.class,null)
                    .commit();
        }
        this.joueurEnCours = joueurEnCours;

    }

}