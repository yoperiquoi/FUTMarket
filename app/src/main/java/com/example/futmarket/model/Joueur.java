package com.example.futmarket.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getDrapeau() {
        return drapeau;
    }

    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getRarete() {
        return rarete;
    }

    public void setRarete(String rarete) {
        this.rarete = rarete;
    }

}
