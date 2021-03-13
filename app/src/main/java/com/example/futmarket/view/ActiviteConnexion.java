package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futmarket.R;
import com.google.firebase.auth.FirebaseAuth;

public class ActiviteConnexion extends AppCompatActivity {

    private FirebaseAuth mAuth; // l'auuthentification avec le fire base
    private EditText mEmail, mPassword; // les champs de texte


    /**
     * la creation de l'activite
     * @param savedInstanceState l'instance a la creation de la vue
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //la creation de l'activite
        setContentView(R.layout.activite_connexion); //recuperer le design de l'activite
        mEmail = findViewById(R.id.email);// affectation du text
        mPassword = findViewById(R.id.motDePasse); // le mot de passe

        mAuth = FirebaseAuth.getInstance();// recuperation de l'instance d'authentification
        seConnecterEmail(); // la connection via le mail


    }

    /**
     * le passage a l'activite de choix des modes
     */
    private void jouer(){
        startActivity(new Intent(ActiviteConnexion.this, ActiviteMode.class));
        finish();
    }

    /**
     * pour se connecter avec l'email
     */
    private void seConnecterEmail(){
        Button connect = findViewById(R.id.Connecter); // on recupere le bouton pour valider la connexion
        connect.setOnClickListener(v-> { // activation on clique sur le bouton
            String email= mEmail.getText().toString(); //recuperation du texte
            String mdp= mPassword.getText().toString(); //recuperation du texte
            if(email.isEmpty() && mdp.isEmpty()){
                mPassword.setError(" mdp doit etre non vide");
                mEmail.setError(" email doit etre non vide");
                return;
            }
            mAuth.signInWithEmailAndPassword(email,mdp).addOnCompleteListener(task -> { //la connexion via l'email
                if(task.isSuccessful()){
                   jouer();//on passe au choix des modes
                }
                else {
                    Toast.makeText(ActiviteConnexion.this,"Email ou Mot de pass ne sont pas correct",Toast.LENGTH_SHORT).show(); //sinon on affiche une notification en expliquant le probleme
                }
            });
        });
    }
}