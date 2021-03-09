package com.example.futmarket.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futmarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActiviteConnexion extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mLogin, mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_connexion);
        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.motDePasse);
        mAuth = FirebaseAuth.getInstance();
        seConnecter();
    }

    private void seConnecter(){
        Button connect = findViewById(R.id.Connecter);
        //Code pour vérifier la connexion
        connect.setOnClickListener(v-> {
            String email= mLogin.getText().toString();
            String mdp= mPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email,mdp).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    startActivity(new Intent(ActiviteConnexion.this, ActiviteMode.class));
                    finish();
                }
                else {
                    Toast.makeText(ActiviteConnexion.this,"Email ou Mot de pass ne sont pas correct",Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}