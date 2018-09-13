package com.example.fatiha.labo1gestionmembres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class EnregistrementdansFichier extends AppCompatActivity {

    BufferedWriter ficSortie=null;
    String fichier= "membres.txt";
    String ligne;
    TextView resultatEnrg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enregistrerdansunfichier);

        resultatEnrg = (TextView) findViewById(R.id.resEnregistrement);

        Intent leInt= getIntent();

        ArrayList<Membre> lMembres = leInt.getParcelableArrayListExtra("LISTE");
        try {
            ficSortie=new BufferedWriter(new OutputStreamWriter(openFileOutput(fichier,MainActivity.MODE_APPEND)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        String eol=System.getProperty("line.separator");

        resultatEnrg.setText(" ");


        if (lMembres.size()!=0) {
            for (int i = 0; i < lMembres.size(); i++) {
                ligne = lMembres.get(i).getNom() + ";"
                        + lMembres.get(i).getPrenom() + ";"
                        + lMembres.get(i).getSexe() + ";"
                        + lMembres.get(i).getFonction() + ";"
                        + lMembres.get(i).getTypeTravail() + ";"
                        + lMembres.get(i).getCommentaire();

                try {
                    ficSortie.write(ligne + eol);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                ficSortie.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  makeText(EnregistrementdansFichier.this,listeMembres.size()+"Membre(s) enregistré(S)",LENGTH_LONG).show();

            resultatEnrg.setText(lMembres.size() + " Membre(s) enregistré(S)");
        }
        else{
            resultatEnrg.setText(" Pas de nouveau membre à enregistrer");
        }

            // Button b = (Button)findViewById(R.id.button2);
        ImageButton b= (ImageButton)findViewById(R.id.imageButton1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnregistrementdansFichier.this.finish();


            }
        });


    }






    }

