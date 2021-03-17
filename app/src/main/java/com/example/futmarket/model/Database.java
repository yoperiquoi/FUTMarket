package com.example.futmarket.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsable des intéraction avec la base de données
 */
public class Database {
    FirebaseDatabase database;
    Authentification auth = new Authentification();
    Utilisateur user;

    /**
     * Listener permettant de savoir quand l'utilisateur est chargé
     */
    public OnUserLoaded listener;

    public Database(){
        database= FirebaseDatabase.getInstance();
    }

    /**
     * Permet d'ajouter un utilisateur se connectant via google
     * @param login login de l'utilisateur
     */
    public void AjouterGoogle(String login){
        DatabaseReference userId=database.getReference("Users").child(auth.getCurrentUser().getUid());
        userId.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("toto", "onDataChange: "+snapshot.child("login").getValue());
                if(login.equals(snapshot.child("login").getValue())){
                    userId.child("login").setValue(login);
                }
                else{
                    userId.child("login").setValue(login);
                    userId.child("credit").setValue(1000000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Permet d'ajouter un utilisateur dans la bdd
     * @param login login de l'utilisateur
     */
    public void AjoutUser(String login){
        DatabaseReference userId=database.getReference("Users").child(auth.getCurrentUser().getUid());
        userId.child("credit").setValue(1000000);
        userId.child("login").setValue(login);

    }

    /**
     * Permet de récupérer la référence de la bdd
     * @param s référence à récupérer
     * @return la référence
     */
    public DatabaseReference getRef(String s){
        return database.getReference(s);
    }

    /**
     * Permet de récupérer l'id de l'utilisateur
     * @return id de l'utilisateur
     */
    public String getUserId(){
        return auth.getCurrentUser().getUid();
    }

    /**
     * Permet d'ajouter un joueur lors de l'ouverture un pack
     * @param obj joueur à ajouter
     */
    public void ajouterJoueur(Object obj){
        DatabaseReference userId=database.getReference("Users").child(getUserId());
        userId.child("joueurs").push().setValue(obj);
    }

    /**
     * Permet de acheter un joueur
     * @param obj joueur à acheter
     */
    public void acheterJoueur(Object obj){
        ajouterJoueur(obj);
        int credits = getUser().getCredit() - ((Joueur)obj).getPrix() ;
        Utilisateur user = getUser();
        user.setCredit(credits);
        Map<String,Object> map = new HashMap<>();
        map.put(getUserId(),user);
        database.getReference("Users").updateChildren(map);
    }

    /**
     * Récupére l'utilisateur courant
     * @return utilisateur
     */
    public Utilisateur getUser() {
       return user;
    }

    /**
     * Récupére l'utilisateur dans la BDD et le stocke dans le métier
     */
    public void fetchUser() {
        DatabaseReference reference = new Database().getRef("Users");
        Task<DataSnapshot> task = reference.child(getUserId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    GenericTypeIndicator<Utilisateur> t = new GenericTypeIndicator<Utilisateur>() {
                    };
                    user = task.getResult().getValue(t);
                    listener.Userloaded();
                }
            }
        });
    }

    /**
     * Interface permettant de savoir quand le chargement de l'utilisateur est terminé
     */
    public interface OnUserLoaded{
        void Userloaded();
    }
}
