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
    public static final int signInCode=10; //le sing in code
    private FirebaseAuth mAuth; // l'auuthentification avec le fire base
    private EditText mLogin, mPassword; // les champs de texte
    private SignInButton signIn; // le bouton de google

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
        signIn= findViewById(R.id.signGoogle);
        mAuth = FirebaseAuth.getInstance();
        seConnecterEmail();
        seConnecterGoogle();

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

    /**
     * pour se connecter avec un compte google
     */
    private void seConnecterGoogle(){
        // Configure Google Sign In

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("818919349037-beq0hbotovvrf0qai5lo69mmbaqgurcj.apps.googleusercontent.com")//on demande le toket du client d'authenthification
                .requestEmail()//on demande l'email de l'utilisateur pour le connecter
                .build(); // la creation de toutes les options pour se connecter a son compte google
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso); // on recupere l'utilisateur

        signIn.setOnClickListener(v->{ // attente de clique sur le bouton
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();  //la recuperation de la fenetre de connexion
            startActivityForResult(signInIntent, signInCode);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == signInCode) {
            Task<GoogleSignInAccount> signTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = signTask.getResult(ApiException.class);
                AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                mAuth.signInWithCredential(authCredential).addOnCompleteListener(task -> jouer());

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}