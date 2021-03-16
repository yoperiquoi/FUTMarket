package com.example.futmarket.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;

public class DetailsJoueurAchat extends Fragment {

    public  DetailsJoueurAchat(){
        super(R.layout.details_joueur_achat);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActiviteFiltre activiteFiltres = (ActiviteFiltre) getContext();
        Joueur joueur = activiteFiltres.getJoueurEnCours();
        ((TextView)view.findViewById(R.id.NomJoueurAchat)).setText(activiteFiltres.getJoueurEnCours().getName());
        ((TextView)view.findViewById(R.id.PositionJoueur)).setText(activiteFiltres.getJoueurEnCours().getPosition());
        ((TextView)view.findViewById(R.id.PoidJoueur)).setText(activiteFiltres.getJoueurEnCours().getPoids());
        ((TextView)view.findViewById(R.id.AgeJoueur)).setText(Integer.toString(activiteFiltres.getJoueurEnCours().getAge()));
        ((TextView)view.findViewById(R.id.TailleJoueur)).setText(activiteFiltres.getJoueurEnCours().getTaille());
    }
}
