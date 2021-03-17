package com.example.futmarket.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Database;
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
    private LinkedList<Joueur> list ;
    private Database db =new Database();
    private DatabaseReference ref = db.getRef("Users").child(db.getUserId()).child("joueurs");
    private  AdaptateurJoueur adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_joueurs);
        list = new LinkedList<>();
        joueurs = findViewById(R.id.lesJoueurs);
        adapter = new AdaptateurJoueur(list,getApplicationContext());
        joueurs.setLayoutManager(new LinearLayoutManager(this));
        joueurs.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Joueur joueur = dataSnapshot.getValue(Joueur.class);
                    list.add(joueur);
                }
                adapter.notifyDataSetChanged();
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
}
