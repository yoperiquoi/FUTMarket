package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.futmarket.R;

public class ActivitePrincipale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_principale);

        allerVersInscription();
    }
    
    private void allerVersInscription(){
        Button connect = findViewById(R.id.inscription);
        connect.setOnClickListener(v-> startActivity(new Intent(ActivitePrincipale.this, ActiviteInscription.class)));
    }
}