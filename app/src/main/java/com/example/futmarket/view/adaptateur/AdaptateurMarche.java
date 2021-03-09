package com.example.futmarket.view.adaptateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Pack;

import java.util.List;

public class AdaptateurMarche extends RecyclerView.Adapter {
    private List<Pack> lesPacks;
    private OnPackListener onPackListener;

    public AdaptateurMarche(List<Pack> lesPacks,OnPackListener onPackListener) {
        this.lesPacks=lesPacks;
        this.onPackListener=onPackListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout leLayout =(LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule_pack,parent,false);
        return new ViewHolderPack(leLayout,onPackListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderPack)holder).getNomPack().setText(lesPacks.get(position).getName());
        ((ViewHolderPack)holder).getPrix().setText(lesPacks.get(position).getPrix());
        ((ViewHolderPack)holder).getDescription().setText(lesPacks.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return lesPacks.size();
    }
}
