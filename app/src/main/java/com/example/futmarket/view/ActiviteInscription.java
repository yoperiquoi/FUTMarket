package com.example.futmarket.view;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActiviteInscription extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mLogin, mPassword;
    private Button btn;
    TextView mConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_inscription);

        mLogin = findViewById(R.id.login);
        mPassword = findViewById(R.id.motDePasse);
        mConnect = findViewById(R.id.connect);
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(ActiviteInscription.this, ActiviteMode.class));
            finish();
        }
        seInscrire();
    }

    private void seInscrire(){
        Button connect = findViewById(R.id.inscription);
        //Code pour vérifier l'inscription

        connect.setOnClickListener(v-> {
            String login =mLogin.getText().toString();
            String mdp =mPassword.getText().toString();
            
            if(TextUtils.isEmpty(login) && TextUtils.isEmpty(mdp)){
                mLogin.setError("Le login");
                mPassword.setError("ou mdp doivent etre non vides");
                return;
            }
            
            mAuth.createUserWithEmailAndPassword(login,mdp).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    startActivity(new Intent(ActiviteInscription.this, ActiviteMode.class));
                    finish();
                }
                else{
                    Toast.makeText(ActiviteInscription.this,"pb"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    public void connection(View view) {
        startActivity(new Intent(ActiviteInscription.this,ActiviteConnexion.class));
        finish();
    }
}