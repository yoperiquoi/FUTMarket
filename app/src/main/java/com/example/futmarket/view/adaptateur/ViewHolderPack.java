package com.example.futmarket.view.adaptateur;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;

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

    public TextView getNomPack(){
        return nomPack;
    }

    public TextView getPrix() {
        return prix;
    }

    public TextView getDescription() {
        return description;
    }

    @Override
    public void onClick(View v) {
        onPackListener.OnClickPack(getAdapterPosition());
    }
}
