package com.example.futmarket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.futmarket.R;
import com.example.futmarket.model.Pack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OuverturePack extends AppCompatActivity {
    private Pack pack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ouverture_pack);

        try(ObjectInputStream ois = new ObjectInputStream(openFileInput(getFilesDir()+"/OuverturePack"))){
            pack=(Pack)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}