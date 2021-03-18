package com.example.futmarket.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.controller.Database;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.view.adaptateur.AdaptateurJoueur;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

/**
 * Classe définissant l'affichage des joueurs possédé par l'utilisateur
 */
public class MesJoueurs extends AppCompatActivity {
    private RecyclerView joueurs;
    public LinkedList<Joueur> list ;
    private Database db =new Database();
    private Joueur joueurEnCours;
    private DatabaseReference ref = db.getRef("Users").child(db.getUserId()).child("joueurs");
    public AdaptateurJoueur adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_joueurs);
        list = new LinkedList<>();
        joueurs = findViewById(R.id.lesJoueurs);
        adapter = new AdaptateurJoueur(list,getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        joueurs.setLayoutManager(new LinearLayoutManager(this));
        joueurs.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Joueur joueur = dataSnapshot.getValue(Joueur.class);
                    list.add(joueur);
                }
                adapter.refreshData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();
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
                    .replace(R.id.container,DetailsJoueurVente.class,null)
                    .commit();
        }
        this.joueurEnCours = joueurEnCours;

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
}
