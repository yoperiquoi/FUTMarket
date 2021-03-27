package com.example.futmarket.view.adaptateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Pack;

import java.util.List;

/**
 * Classe permettant l'affichage des packs
 */
public class AdaptateurMarche extends RecyclerView.Adapter {
    private List<Pack> lesPacks;
    private OnPackListener onPackListener;

    /**
     * initialization de Adaptaterur du marche
     * @param lesPacks la liste des packs
     * @param onPackListener le listener du pack
     */
    public AdaptateurMarche(List<Pack> lesPacks,OnPackListener onPackListener) {
        this.lesPacks=lesPacks;
        this.onPackListener=onPackListener;
    }

    /**
     * la creation de la viewHolder
     * @return le nouveau viewHolder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout leLayout =(LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule_pack,parent,false);
        return new ViewHolderPack(leLayout,onPackListener);
    }

    /**
     * on relie la view a l'adaptateur
     * @param holder viewHolder
     * @param position int position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderPack)holder).getNomPack().setText(lesPacks.get(position).getName()); // on affecte le nom du pack a la view
        ((ViewHolderPack)holder).getPrix().setText(lesPacks.get(position).getPrix()); // on affecte le prix du pack a la view
        ((ViewHolderPack)holder).getDescription().setText(lesPacks.get(position).getDescription()); // on affecte la description du pack a la view
    }

    /**
     * Permet de notifier la liste d'un changement de données
     * @param newPacks nouvelle liste
     */
    public void refreshData(List<Pack> newPacks){
        lesPacks = newPacks;
        notifyDataSetChanged();
    }

    /**
     * on recupere la taille de la liste des pack
     * @return la taille des packs
     */
    @Override
    public int getItemCount() {
        return lesPacks.size();
    }
}
