package com.example.futmarket.model;

import java.io.Serializable;

public class Joueur implements Serializable {
    private String name;
    private int age;
    private String position;
    private String club;
    private int taille;
    private int poids;
    private int note;
    private Float prix;
    private Rarete rarete;

    public Joueur(String name, int age, String position, String club, int taille, int poids, int note, Float prix,Rarete rarete) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.club = club;
        this.taille = taille;
        this.poids = poids;
        this.note = note;
        this.prix = prix;
        this.rarete=rarete;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}
