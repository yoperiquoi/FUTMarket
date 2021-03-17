package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.futmarket.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Classe permettant de sélectionner un mode
 */
public class ActiviteMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_mode);
        selectionPack();
        selectionMarket();
        mesjoueurs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();


    }

    /**
     * Permet de se diriger vers le marché de packs
     */
    private void selectionPack(){
        Button pack = findViewById(R.id.modePack);

        pack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),SelectionPack.class));
        });
    }

    /**
     * Permet de se diriger vers le marché des joueurs
     */
    private void selectionMarket(){
        Button market = findViewById(R.id.modeMarket);
        market.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),ActiviteFiltre.class)));
    }

    /**
     * Permet de se diriger vers l'afficage de ces joueurs
     */
    private void mesjoueurs(){
        Button joueurs = findViewById(R.id.joueurs);
        joueurs.setOnClickListener(v->startActivity(new Intent(getApplicationContext(),MesJoueurs.class)));
    }
}