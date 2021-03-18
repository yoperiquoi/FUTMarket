package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.controller.ManagerJoueur;
import com.example.futmarket.controller.MarchePack;
import com.example.futmarket.view.adaptateur.AdaptateurJoueur;

import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Classe reponsable de l'affichage du marché de joueur
 */
public class ActiviteMarche extends AppCompatActivity {
    ManagerJoueur manager;
    List<Joueur> lesJoueurs ;
    private Joueur joueurEnCours;
    private AdaptateurJoueur adapter;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_marche);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RecyclerView laListView = findViewById(R.id.listView3);
        progress = findViewById(R.id.progress2);


        laListView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdaptateurJoueur(new LinkedList<>(),getApplicationContext());
        laListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: test");
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
                    .replace(R.id.container,DetailsJoueurAchat.class,null)
                    .commit();
        }
        this.joueurEnCours = joueurEnCours;
    }

}