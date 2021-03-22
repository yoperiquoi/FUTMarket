package com.example.futmarket.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.futmarket.R;
import com.example.futmarket.model.Authentification;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.model.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
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
    public void acheterJoueur(Object obj,View view){
        ajouterJoueur(obj);
        listener = new OnUserLoaded() {
            @Override
            public void Userloaded() {
                if (getUser().getCredit() - ((Joueur)obj).getPrix() < 0){
                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.PasCredit)+ Integer.toString(((Joueur)obj).getPrix()) +view.getContext().getString(R.string.credits) ,Toast.LENGTH_SHORT).show();
                    return;
                }
                int credits = getUser().getCredit() - ((Joueur)obj).getPrix() ;
                Utilisateur user = getUser();
                user.setCredit(credits);
                Map<String,Object> map = new HashMap<>();
                map.put(getUserId(),user);
                database.getReference("Users").updateChildren(map);
                Toast.makeText(view.getContext(), view.getContext().getString(R.string.achetePour)+ Integer.toString(((Joueur)obj).getPrix()) +view.getContext().getString(R.string.credits) ,Toast.LENGTH_SHORT).show();
            }
        };
        fetchUser();
    }

    /**
     * Méthode permettant de retirer des crédit à un utilisateur
     * @param credit crédit a retirer
     * @param context context de l'application
     */
    public void retirerCredits(int credit, Context context){
        listener = new OnUserLoaded() {
            @Override
            public void Userloaded() {
                int credits= getUser().getCredit() - credit;
                Utilisateur user = getUser();
                user.setCredit(credits);
                Map<String,Object> map = new HashMap<>();
                map.put(getUserId(),user);
                database.getReference("Users").updateChildren(map);
                Toast.makeText(context, context.getString(R.string.coutPack)+ Integer.toString(credit) +context.getString(R.string.credits) ,Toast.LENGTH_SHORT).show();
            }
        };
        fetchUser();
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
     * Methode permettant de vendre un joueur
     * @param joueur joueur a vendre
     * @param v la vue permettant d'afficher les notification
     */
    public void vendreJoueur(Joueur joueur, View v) {
        listener = new OnUserLoaded() {
            @Override
            public void Userloaded() {
                HashMap<String,Joueur> lesJoueurs = user.getJoueurs();

                for (Map.Entry<String, Joueur> entries :lesJoueurs.entrySet()){
                    if (entries.getValue().equals(joueur)){
                        String id=entries.getKey();
                        lesJoueurs.remove(id);
                        getUser().setJoueurs(lesJoueurs);

                        Utilisateur user = getUser();
                        user.setCredit(getUser().getCredit() + ((Joueur)joueur).getPrix());
                        Map<String,Object> map = new HashMap<>();
                        map.put(getUserId(),user);
                        database.getReference("Users").updateChildren(map);

                        Toast.makeText(v.getContext(), joueur.getName()+ v.getContext().getString(R.string.venduPour) +Integer.toString(((Joueur)joueur).getPrix()) +v.getContext().getString(R.string.credits) ,Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        };
        fetchUser();
    }

    /**
     * Interface permettant de savoir quand le chargement de l'utilisateur est terminé
     */
    public interface OnUserLoaded{
        void Userloaded();
    }
}
