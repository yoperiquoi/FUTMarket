package com.example.futmarket.view.adaptateur;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.view.ActiviteFiltre;
import com.example.futmarket.view.MesJoueurs;

/**
 * Classe définissant l'affichage d'un joueur
 */
public class ViewHolderJoueur extends RecyclerView.ViewHolder {
    private TextView nomJoueur;
    private TextView prixJoueur;
    private TextView overall;
    private ImageView drapeau;
    private ImageView logoClub;
    private ImageView photo;


    public ViewHolderJoueur(@NonNull View itemView) {
        super(itemView);
        nomJoueur=itemView.findViewById(R.id.NomJoueur);
        prixJoueur=itemView.findViewById(R.id.PrixJoueur);
        overall=itemView.findViewById(R.id.Overall);
        drapeau=itemView.findViewById(R.id.DrapeauJoueur);
        logoClub=itemView.findViewById(R.id.ClubJoueur);
        photo=itemView.findViewById(R.id.ImageJoueur);

    }

    public TextView getNomJoueur() {
        return nomJoueur;
    }

    public TextView getPrixJoueur() {
        return prixJoueur;
    }

    public TextView getOverall() {
        return overall;
    }

    public ImageView getDrapeau() {
        return drapeau;
    }

    public ImageView getLogoClub() {
        return logoClub;
    }

    public ImageView getPhoto() {
        return photo;
    }

    /**
     * Permet le clic sur la photo pour accéder au détails
     * @param joueurCourant joueur associé à la photo
     */
    public void setJoueurCourant(Joueur joueurCourant){
        if (nomJoueur.getContext().getClass() == ActiviteFiltre.class ) {
            photo.setOnClickListener(v -> {
                ((ActiviteFiltre) (nomJoueur.getContext())).setJoueurEnCours(joueurCourant);
            });
        }
        if(nomJoueur.getContext().getClass() == MesJoueurs.class){
            photo.setOnClickListener(v -> {
                ((MesJoueurs) (nomJoueur.getContext())).setJoueurEnCours(joueurCourant);
            });
        }
    }


}
