package com.example.futmarket.view.adaptateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;

import java.util.LinkedList;
import java.util.List;

public class AdaptateurJoueur extends RecyclerView.Adapter {
    private List<Joueur> lesJoueurs;

    public AdaptateurJoueur(LinkedList<Joueur> joueurs) {
        this.lesJoueurs=joueurs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout leLayout =(LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule_pack,parent,false);
        return new ViewHolderJoueur(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lesJoueurs.size();
    }
}
