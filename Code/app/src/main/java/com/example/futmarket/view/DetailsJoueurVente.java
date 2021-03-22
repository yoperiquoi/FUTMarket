package com.example.futmarket.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.futmarket.R;
import com.example.futmarket.controller.Database;
import com.example.futmarket.model.Joueur;

/**
 * Classe définissant l'affichage du fragment du detail du joueur
 */
public class DetailsJoueurVente extends Fragment {

    public  DetailsJoueurVente(){
        super(R.layout.details_joueur_achat);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MesJoueurs mesJoueurs = (MesJoueurs) getContext();
        Joueur joueur = mesJoueurs.getJoueurEnCours();
        ((TextView)view.findViewById(R.id.NomJoueurAchat)).setText(mesJoueurs.getJoueurEnCours().getName());
        ((TextView)view.findViewById(R.id.PositionJoueur)).setText(mesJoueurs.getJoueurEnCours().getPosition());
        ((TextView)view.findViewById(R.id.PoidJoueur)).setText(mesJoueurs.getJoueurEnCours().getPoids());
        ((TextView)view.findViewById(R.id.AgeJoueur)).setText(Integer.toString(mesJoueurs.getJoueurEnCours().getAge()));
        ((TextView)view.findViewById(R.id.TailleJoueur)).setText(mesJoueurs.getJoueurEnCours().getTaille());
        ((TextView)view.findViewById(R.id.button)).setText(R.string.vendre);
        ((TextView)view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database().vendreJoueur(joueur,v);
                mesJoueurs.finish();
                mesJoueurs.startActivity(mesJoueurs.getIntent());
            }
        });
    }


    
    
}

