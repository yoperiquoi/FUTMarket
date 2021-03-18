package com.example.futmarket.view.adaptateur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;


import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Adapteur permettant l'affichage d'un joueur
 */
public class AdaptateurJoueur extends RecyclerView.Adapter implements Filterable{
    private List<Joueur> lesJoueurs;
    private List<Joueur> lesJoueursAll;
    private Context context;
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Joueur> filteredList = new LinkedList<>();

            if (constraint.toString().isEmpty()){
                filteredList.addAll(lesJoueursAll);
            }else{
                for(Joueur joueur : lesJoueursAll){
                    if(joueur.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(joueur);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();

            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lesJoueurs.clear();
            lesJoueurs.addAll((Collection<? extends Joueur>) results.values);
            notifyDataSetChanged();
        }
    };

    public AdaptateurJoueur(LinkedList<Joueur> joueurs, Context applicationContext) {
        this.lesJoueurs=joueurs;
        this.lesJoueursAll = joueurs;
        context= applicationContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout leLayout =(RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule_joueur,parent,false);
        return new ViewHolderJoueur(leLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderJoueur)holder).setJoueurCourant(lesJoueurs.get(position));
        ((ViewHolderJoueur)holder).getNomJoueur().setText(lesJoueurs.get(position).getName());
        ((ViewHolderJoueur)holder).getPrixJoueur().setText(context.getString(R.string.valeur)+(Integer.toString(lesJoueurs.get(position).getPrix()))+ context.getString(R.string.euro));
        ((ViewHolderJoueur)holder).getOverall().setText(context.getString(R.string.note)+(Integer.toString(lesJoueurs.get(position).getNote())));
        new DownloadImageTask(((ViewHolderJoueur)holder).getPhoto())
                .execute(lesJoueurs.get(position).getPhoto());
        new DownloadImageTask(((ViewHolderJoueur)holder).getLogoClub())
                .execute(lesJoueurs.get(position).getClub());
        new DownloadImageTask(((ViewHolderJoueur)holder).getDrapeau())
                .execute(lesJoueurs.get(position).getDrapeau());

        switch (lesJoueurs.get(position).getRarete()){
            case "Bronze":
                holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.bronze));
                break;
            case "Argent":
                holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.argent));
                break;
            case "Or":
                holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.or));
                break;
            case "Legende":
                holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.legende));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lesJoueurs.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    /**
     * Classe interne permettant le téléchargement d'image à partir d'une URL
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                mIcon11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    /**
     * Permet de notifier la liste d'un changement de données
     * @param newJoueurs nouvelle liste
     */
    public void refreshData(List<Joueur> newJoueurs){
        lesJoueurs = newJoueurs;
        lesJoueursAll = new LinkedList<>(newJoueurs);
        notifyDataSetChanged();
    }
}

