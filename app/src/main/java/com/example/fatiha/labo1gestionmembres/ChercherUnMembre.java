package com.example.fatiha.labo1gestionmembres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChercherUnMembre extends AppCompatActivity {
    BufferedReader ficEntree=null;
    String fichier= "membres.txt";
    ArrayList<Membre> ListePourTrait= new ArrayList<Membre>();
    TextView nomrecherche,prenomrecherche,affichage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_selon_nom_prenom);
        try {
            ChargementArrayFichier();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nomrecherche= (TextView) findViewById(R.id.nomrecherche);
        prenomrecherche= (TextView) findViewById(R.id.prenomrecherhe);
        affichage= (TextView)findViewById(R.id.amembreCherche);

        ImageButton b= (ImageButton)findViewById(R.id.imageButton2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChercherUnMembre.this.finish();


            }
        });

        ImageButton ib1=(ImageButton)findViewById(R.id.imageButton4);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomrecherche.setText(" ");
                prenomrecherche.setText(" ");
                affichage.setText(" ");
            }
        });




        ImageButton ib=(ImageButton)findViewById(R.id.imageButton3);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultat="";

                for (int i=0;i< ListePourTrait.size();i++){


                    if (ListePourTrait.get(i).getNom().equalsIgnoreCase(nomrecherche.getText().toString().trim())) {
                        if (ListePourTrait.get(i).getPrenom().equalsIgnoreCase(prenomrecherche.getText().toString().trim())){
                            resultat += "Nom : " + ListePourTrait.get(i).getNom() + "\n" +
                                    "Prenom : " + ListePourTrait.get(i).getPrenom() + "\n" +
                                    "Sexe :" + ListePourTrait.get(i).getSexe() + "\n" +
                                    "Fonction : " + ListePourTrait.get(i).getFonction() + "\n" +
                                    "Type de travail: " + ListePourTrait.get(i).getTypeTravail() + "\n" +
                                    "Commentaires : " + ListePourTrait.get(i).getCommentaire() + "\n" +
                                    "****************************************************\n";
                        }
                    }

                }
                if (resultat.length()==0) {
                    affichage.setText(" Membre inexistant");
                }
                else {
                    affichage.setText(resultat);
                }

            }


        });

    }


    private void ChargementArrayFichier()throws IOException {

        ficEntree = new BufferedReader(new InputStreamReader(openFileInput(fichier)));

        String ligne,nom,prenom,sexe,fct,typedetravail,comm;

        StringTokenizer strTok;

        ligne=ficEntree.readLine();

        while (ligne !=null){

            strTok=new StringTokenizer(ligne,";");
            nom=strTok.nextToken();
            prenom= strTok.nextToken();
            sexe=strTok.nextToken();
            fct=strTok.nextToken();
            typedetravail=strTok.nextToken();
            comm=strTok.nextToken();



            Membre unMembre;
            unMembre = new Membre( nom,prenom, sexe, fct,typedetravail,comm);

            ListePourTrait.add(unMembre);



            ligne=ficEntree.readLine();
        }

        ficEntree.close();
    }




}
