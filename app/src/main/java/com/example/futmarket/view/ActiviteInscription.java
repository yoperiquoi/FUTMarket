package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.futmarket.R;

public class ActiviteInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_inscription);
        seInscrire();
    }

    private void seInscrire(){
        Button connect = findViewById(R.id.inscription);
        //Code pour vérifier l'inscription
        connect.setOnClickListener(v-> startActivity(new Intent(ActiviteInscription.this, ActiviteMode.class)));
    }
}