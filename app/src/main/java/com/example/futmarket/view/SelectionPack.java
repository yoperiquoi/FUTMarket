package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.futmarket.R;
import com.example.futmarket.model.MarchePack;
import com.example.futmarket.model.Pack;
import com.example.futmarket.model.Stub;
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

    @Override
    public void OnClickPack(int position) {

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