package com.example.futmarket.view.adaptateur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;


import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class AdaptateurJoueur extends RecyclerView.Adapter {
    private List<Joueur> lesJoueurs;
    private Context context;

    public AdaptateurJoueur(LinkedList<Joueur> joueurs, Context applicationContext) {
        this.lesJoueurs=joueurs;
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
        ((ViewHolderJoueur)holder).getNomJoueur().setText(lesJoueurs.get(position).getName());
        ((ViewHolderJoueur)holder).getPrixJoueur().setText(context.getString(R.string.valeur)+(Integer.toString(lesJoueurs.get(position).getPrix()))+ context.getString(R.string.euro));
        ((ViewHolderJoueur)holder).getOverall().setText(context.getString(R.string.note)+(Integer.toString(lesJoueurs.get(position).getNote())));
        new DownloadImageTask(((ViewHolderJoueur)holder).getPhoto())
                .execute(lesJoueurs.get(position).getPhoto());
        new DownloadImageTask(((ViewHolderJoueur)holder).getLogoClub())
                .execute(lesJoueurs.get(position).getClub());
        new DownloadImageTask(((ViewHolderJoueur)holder).getDrapeau())
                .execute(lesJoueurs.get(position).getDrapeau());

    }

    @Override
    public int getItemCount() {
        return lesJoueurs.size();
    }

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
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    
    }
}

