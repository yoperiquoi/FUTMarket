package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.futmarket.R;
import com.example.futmarket.controller.Database;
import com.example.futmarket.controller.MarchePack;
import com.example.futmarket.model.Pack;
import com.example.futmarket.view.adaptateur.AdaptateurMarche;
import com.example.futmarket.view.adaptateur.OnPackListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe définissant l'affichage du marché de pack
 */
public class SelectionPack extends AppCompatActivity implements OnPackListener {
    private MarchePack marchePack = new MarchePack();
    private static final String TAG = "SelectionPack";

    private AdaptateurMarche adapter;

    private ProgressBar progress;

    /**
     * la creation de la fenetre d'application on met en place tous les parties graphiques
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_selection_pack);
        RecyclerView laListView = findViewById(R.id.listView);
        progress = findViewById(R.id.progress);
        laListView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdaptateurMarche(new ArrayList<>(),this);
        laListView.setAdapter(adapter);
    }

    /**
     * a la continuation de l'activite on utilise le fragment
     */
    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();

        marchePack= new MarchePack();
        adapter.refreshData(new LinkedList<>());

        progress.setVisibility(View.VISIBLE);

        marchePack.listener = new MarchePack.OnMarketGeneratedListener() {
            @Override
            public void onPackGenerated() {
                List<Pack> packs = marchePack.getLesPacks();
                adapter.refreshData(packs);
                progress.setVisibility(View.GONE);
            }
        };
        marchePack.generatePacks();
    }

    /**
     * en le clique sur le pack pour obtenir les joueurs
     * @param position la position du pack dans la liste des packs
     */
    @Override
    public void OnClickPack(int position) {
        Database db = new Database();

        db.listener= new Database.OnUserLoaded() {
            @Override
            public void Userloaded() {
                if(db.getUser().getCredit() < marchePack.getLesPacks().get(position).getPrixPack()){
                    Toast.makeText(getApplicationContext(), getString(R.string.creditInsuffisant)+ Integer.toString(marchePack.getLesPacks().get(position).getPrixPack()) +getString(R.string.credits) ,Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    return;
                }
                db.retirerCredits(marchePack.getLesPacks().get(position).getPrixPack(),getApplicationContext());
            }

        };

        db.fetchUser();

        Intent intent = new Intent(this, OuverturePack.class);

        File file = new File(getFilesDir()+"/OuverturePack");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFilesDir()+"/OuverturePack"))) {
            oos.writeObject(marchePack.getLesPacks().get(position));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };

        startActivity(intent);

    }

}