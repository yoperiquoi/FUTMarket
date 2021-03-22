package com.example.futmarket.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.futmarket.R;
import com.example.futmarket.controller.Database;
import com.example.futmarket.model.Utilisateur;

/**
 * Classe définissant l'affichage des infos des utilisateurs
 */
public class InfosUtilisateur extends Fragment {
    Utilisateur user;

    public InfosUtilisateur() {
        super(R.layout.infos_utilisateur);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onResume();
        Database database = new Database();
        database.listener = new Database.OnUserLoaded() {
            @SuppressLint("SetTextI18n")
            @Override
            public void Userloaded() {
                user = database.getUser();
                TextView textview =(TextView) getView().findViewById(R.id.pseudo);
                TextView textView2 = (TextView) getView().findViewById(R.id.nbCredit);
                TextView textView3 =  (TextView) getView().findViewById(R.id.credit);
                textview.setText(user.getLogin());
                textView3.setText(R.string.credits);
                textView2.setText(Integer.toString(user.getCredit()));
            }
        };
        database.fetchUser();
    }
}



