package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.futmarket.R;

public class ActiviteConnexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_connexion);
        seConnecter();
    }

    private void seConnecter(){
        Button connect = findViewById(R.id.Connecter);
        //Code pour vérifier la connexion
        connect.setOnClickListener(v-> startActivity(new Intent(ActiviteConnexion.this, ActiviteMode.class)));
    }
}