package com.example.futmarket.view.adaptateur;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;

/**
 * Classe définissant l'affichage d'un pack
 */
public class ViewHolderPack extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView nomPack;
    private TextView prix;
    private TextView description;

    OnPackListener onPackListener;

    public ViewHolderPack(@NonNull View itemView, OnPackListener onPackListener){
        super(itemView);
        nomPack=itemView.findViewById(R.id.NomPack);
        prix=itemView.findViewById(R.id.PrixPack);
        description=itemView.findViewById(R.id.DescriptionPack);
        this.onPackListener=onPackListener;

        itemView.setOnClickListener(this);
    }

    /**
     * getteur de nom du pack
     * @return le nom du pack
     */
    public TextView getNomPack(){
        return nomPack;
    }

    /**
     * getteur du prix du pack
     * @return le prix du pack
     */
    public TextView getPrix() {
        return prix;
    }

    /**
     * getteur de description du pack
     * @return la description du pack
     */
    public TextView getDescription() {
        return description;
    }

    /**
     * onClick sur la view
     * @param v la view
     */
    @Override
    public void onClick(View v) {
        onPackListener.OnClickPack(getAdapterPosition());
    }
}
