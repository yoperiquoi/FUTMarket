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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActiviteConnexion extends AppCompatActivity {
    public static final int signInCode=10;
    private FirebaseAuth mAuth;
    private EditText mLogin, mPassword;
    private SignInButton signIn;
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

    private void jouer(){
        startActivity(new Intent(ActiviteConnexion.this, ActiviteMode.class));
        finish();
    }

    private void seConnecterEmail(){
        Button connect = findViewById(R.id.Connecter);
        //Code pour vérifier la connexion
        connect.setOnClickListener(v-> {
            String email= mLogin.getText().toString();
            String mdp= mPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email,mdp).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                   jouer();
                }
                else {
                    Toast.makeText(ActiviteConnexion.this,"Email ou Mot de pass ne sont pas correct",Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    private void seConnecterGoogle(){
        // Configure Google Sign In

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signIn.setOnClickListener(v->{
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, signInCode);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == signInCode) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                jouer();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}