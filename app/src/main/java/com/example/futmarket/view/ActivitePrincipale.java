package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.futmarket.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class ActivitePrincipale extends AppCompatActivity {
    FirebaseAuth utilisateur; // variable de Firebase

    /**
     * la creation de la vue
     * @param savedInstanceState l'instance de l'application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utilisateur = FirebaseAuth.getInstance(); //l'instance d'authentication de Firebase
        setContentView(R.layout.activite_principale); // on definit la vue a partir d'un ficher xml
        allerVersInscription();
    }

    /**
     * aller vers la vue d'inscription
     */
    private void allerVersInscription(){
        Button connect = findViewById(R.id.inscription); //on recupere le bouton pour valider l'inscription
        connect.setOnClickListener(v-> startActivity(new Intent(ActivitePrincipale.this, ActiviteInscription.class)));// on met un lister pour executer le code quand on clique sur bouton
    }

    /**
     * la deconnection de firebase et du google
     * @param view la vue du bouton qui est reliée avec la methode onclick dans le fichier xml de l'activite
     */
    public void deconnection(View view) {
        if(utilisateur.getCurrentUser() != null){ // si l'utilisateur est connecté
            FirebaseAuth.getInstance().signOut();// on se deconnecte de firebase
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
            GoogleSignIn.getClient(this, gso).signOut();//on se deconnecte du google
            Toast.makeText(getApplicationContext(),"Deconncté",Toast.LENGTH_SHORT).show(); //ca fait un notification que c'est deconnecté
        }
        else{//sinon
            Toast.makeText(getApplicationContext(),"Vous n'etes pas connecté",Toast.LENGTH_SHORT).show();//ca fait un pop up qu'on etait pas connecté
        }
    }
}