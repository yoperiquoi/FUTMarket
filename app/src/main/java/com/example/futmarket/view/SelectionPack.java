package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.futmarket.R;
import com.example.futmarket.model.MarchePack;
import com.example.futmarket.model.Stub;
import com.example.futmarket.view.adaptateur.AdaptateurMarche;
import com.example.futmarket.view.adaptateur.OnPackListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SelectionPack extends AppCompatActivity implements OnPackListener {
    private MarchePack marchePack = new MarchePack().generatePacks();
    private static final String TAG = "SelectionPack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_selection_pack);
        RecyclerView laListView = findViewById(R.id.listView);

        laListView.setLayoutManager(new LinearLayoutManager(this));
        laListView.setAdapter(new AdaptateurMarche(marchePack.getLesPacks(),this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new InfosUtilisateur(),null).commit();


    }

    @Override
    public void OnClickPack(int position) {
        Log.d(TAG, "OnClickPack: clicked" + getFilesDir()+"/OuverturePack");

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
        } ;

        startActivity(intent);

    }

}