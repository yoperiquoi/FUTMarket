package com.example.futmarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.futmarket.R;
import com.example.futmarket.model.Utilisateur;

public class InfosUtilisateur extends Fragment {
    private Utilisateur user= new Utilisateur();
    private TextView pseudo,credits;
    private String login,cr;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.infos_utilisateur,container,false);
        pseudo = (TextView) view.findViewById(R.id.pseudo);
        credits = (TextView) view.findViewById(R.id.credits);
        return view;
    }
}
