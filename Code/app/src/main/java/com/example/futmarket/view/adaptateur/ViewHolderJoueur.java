package com.example.futmarket.view.adaptateur;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futmarket.R;
import com.example.futmarket.model.Joueur;
import com.example.futmarket.view.ActiviteMarche;
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

    /**
     * initialization de viewHolder du joueur
     * @param itemView
     */
    public ViewHolderJoueur(@NonNull View itemView) {
        super(itemView);
        nomJoueur=itemView.findViewById(R.id.NomJoueur);
        prixJoueur=itemView.findViewById(R.id.PrixJoueur);
        overall=itemView.findViewById(R.id.Overall);
        drapeau=itemView.findViewById(R.id.DrapeauJoueur);
        logoClub=itemView.findViewById(R.id.ClubJoueur);
        photo=itemView.findViewById(R.id.ImageJoueur);

    }
    /**
     * getteur du nom du joueur
     * @return TextView avec le nom du joueur
     */
    public TextView getNomJoueur() {
        return nomJoueur;
    }

    /**
     * getteur le prix du joueur
     * @return TextView avec le prix du joueur
     */
    public TextView getPrixJoueur() {
        return prixJoueur;
    }

    /**
     * getteur la note(rating) du joueur
     * @return TextView avec le rating du joueur
     */
    public TextView getOverall() {
        return overall;
    }

    /**
     * getteur le pays du joueur
     * @return TextView avec le pays du joueur
     */
    public ImageView getDrapeau() {
        return drapeau;
    }

    /**
     * getteur le club du joueur
     * @return ImageView avec le club du joueur
     */
    public ImageView getLogoClub() {
        return logoClub;
    }

    /**
     * getteur la photo du joueur
     * @return ImageView avec la photo du joueur
     */
    public ImageView getPhoto() {
        return photo;
    }

    /**
     * Permet le clic sur la photo pour accéder au détails
     * @param joueurCourant joueur associé à la photo
     */
    public void setJoueurCourant(Joueur joueurCourant){
        if (nomJoueur.getContext().getClass() == ActiviteMarche.class ) {
            photo.setOnClickListener(v -> { // pour executer l'actiion sur joueur on clique sur sa photo sur le marché
                ((ActiviteMarche) (nomJoueur.getContext())).setJoueurEnCours(joueurCourant);
            });
        }
        if(nomJoueur.getContext().getClass() == MesJoueurs.class){
            photo.setOnClickListener(v -> { // pour executer l'actiion sur joueur on clique sur sa photo dans les joueurs de l'utilisateur
                ((MesJoueurs) (nomJoueur.getContext())).setJoueurEnCours(joueurCourant);
            });
        }
    }


}
