package com.example.futmarket.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futmarket.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class ActiviteConnexion extends AppCompatActivity {

    private FirebaseAuth mAuth; // l'auuthentification avec le fire base
    private EditText mLogin, mPassword; // les champs de texte


    /**
     * la creation de l'activite
     * @param savedInstanceState l'instance a la creation de la vue
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_connexion);
        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.motDePasse);

        mAuth = FirebaseAuth.getInstance();
        seConnecterEmail();


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
            String email= mLogin.getText().toString(); //recuperation du texte
            String mdp= mPassword.getText().toString(); //recuperation du texte
            mAuth.signInWithEmailAndPassword(email,mdp).addOnCompleteListener(task -> { //la connexion depuis l'email
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