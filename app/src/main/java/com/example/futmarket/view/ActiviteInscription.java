package com.example.futmarket.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futmarket.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class ActiviteInscription extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mLogin, mPassword;
    private Button btn;
    TextView mConnect;
    private SignInButton signIn; // le bouton de google
    public static final int signInCode=10; //le sing in code

    /**
     * la creation de l'activite d'inscription
     * @param savedInstanceState l'instance a la creation de la vue
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_inscription);
        signIn= findViewById(R.id.signGoogle);
        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.motDePasse);
        mConnect = findViewById(R.id.connect);
        mAuth = FirebaseAuth.getInstance();


        checkConnect();

        seInscrire();
        seConnecterGoogle();
    }

    /**
     * pour l'inscrire dans l'application avec son propre email
     */
    private void seInscrire(){
        Button connect = findViewById(R.id.inscription); //on recherche le bouton pour valider l'inscription
        //Code pour vérifier l'inscription

        connect.setOnClickListener(v-> {
            String login =mLogin.getText().toString(); // on met le texte ecrit depuis le champ du texte dans un string
            String mdp =mPassword.getText().toString(); // on met le texte ecrit depuis le champ du texte dans un string
            
            if(login.isEmpty() && mdp.isEmpty()){ //on met l'erreur si login ou mot de passe sont vides
                mLogin.setError("L'email doit etre non vide");
                mPassword.setError(" mdp doit etre non vide");
                return;
            }
            
            mAuth.createUserWithEmailAndPassword(login,mdp).addOnCompleteListener(task -> { //on cree l'utilisateur depuis son email et son mot de passe
                if(task.isSuccessful()){ //si l'utilisateur est bien cree on va sur l'activite de choix des modes
                    startActivity(new Intent(ActiviteInscription.this, ActiviteMode.class));
                    finish();
                }
                else{ // sinon  il y a une notification qui apparait
                    Toast.makeText(ActiviteInscription.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    /**
     * passage a l'activite de connection a l'application
     * @param view la view de bouton
     */
    public void connection(View view) {
        startActivity(new Intent(getApplicationContext(),ActiviteConnexion.class));
    }


    /**
     * le passage a l'activite de choix des modes
     */
    private void jouer(){
        startActivity(new Intent(ActiviteInscription.this, ActiviteMode.class));
        finish();
    }

    /**
     * on verifie si l'utilisateur est connecte depuis son email ou avec google
     */
    public void checkConnect(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(mAuth.getCurrentUser() != null || account != null){
            jouer();
        }
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