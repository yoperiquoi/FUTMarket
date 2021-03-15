package com.example.futmarket.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.futmarket.R;
import com.example.futmarket.model.Database;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.model.MarchePack;
import com.example.futmarket.model.Pack;
import com.example.futmarket.model.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import org.w3c.dom.Text;

import java.util.List;

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



