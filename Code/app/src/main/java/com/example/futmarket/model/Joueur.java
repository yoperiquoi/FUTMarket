package com.example.futmarket.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe définisant un joueur de foot
 */
public class Joueur implements Serializable {
    private String name;
    private int age;
    private int note;
    private String position;
    private String photo;
    private String club;
    private String drapeau;
    private String taille;
    private String poids;
    private int prix;
    private String rarete;

    /**
     * initialisation d'un Joueur
     * @param name du joueur
     * @param age du joueur
     * @param note du joueur
     * @param position du joueur
     * @param photo du joueur
     * @param club du joueur
     * @param drapeau du joueur
     * @param taille du joueur
     * @param poids du joueur
     * @param prix du joueur
     * @param rarete du joueur
     */
    public Joueur(String name, int age, int note, String position, String photo, String club, String drapeau, String taille, String poids, int prix, String rarete) {
        this.name = name;
        this.age = age;
        this.note = note;
        this.position = position;
        this.photo = photo;
        this.club = club;
        this.drapeau = drapeau;
        this.taille = taille;
        this.poids = poids;
        this.prix = prix;
        this.rarete = rarete;
    }

    public Joueur(){ }

    /**
     * getteur du nom du joueur
     * @return nom
     */
    public String getName() {
        return name;
    }

    /**
     * setteur du nom du joueur
     * @param name du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getteur de l'age du joueur
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * getteur de la note du joueur
     * @return note du joueur
     */
    public int getNote() {
        return note;
    }

    /**
     * getteur de la position du joueur
     * @return position du joueur
     */
    public String getPosition() {
        return position;
    }

    /**
     * getteur de la photo du joueur
     * @return photo du joueur
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * getteur du club du joueur
     * @return club du joueur
     */
    public String getClub() {
        return club;
    }

    /**
     * getteur de la nationalite du joueur
     * @return la nationalite du joueur
     */
    public String getDrapeau() {
        return drapeau;
    }

    /**
     * getteur de la taille du joueur
     * @return la taille du joueur
     */
    public String getTaille() {
        return taille;
    }

    /**
     * getteur du poids d'un joueur
     * @return le poids du joueur
     */
    public String getPoids() {
        return poids;
    }

    /**
     * getteur du prix du joueur
     * @return prix du joueur
     */
    public int getPrix() {
        return prix;
    }

    /**
     * getteur de la rarete du joueur ( bronze, argent ou or)
     * @return la rarete du joueur
     */
    public String getRarete() {
        return rarete;
    }

    /**
     * comparateur du nom du joueur
     * @param obj objet a comparer
     * @return true ou false
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        return getName().equals(((Joueur)obj).getName());
    }
}
