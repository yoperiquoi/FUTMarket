package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.futmarket.R;
import com.google.firebase.auth.FirebaseAuth;

public class ActiviteMode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_mode);

        selectionPack();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();


    }
    private void selectionPack(){
        Button pack = findViewById(R.id.modePack);
        pack.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),SelectionPack.class)));
    }
}