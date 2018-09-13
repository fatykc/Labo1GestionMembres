package com.example.fatiha.labo1gestionmembres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ListeMembresFeminins extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView maListeViewfct;
    Spinner monSpinner;
    BufferedReader ficEntree=null;
    String fichier= "membres.txt";
    ArrayList<Membre> ListePourTrait= new ArrayList<Membre>();
    ImageButton bLF,bSF;
    private int fctChoisie;
    private String lfctChoisie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_membres_feminins);

        /**********************Remplir le ArrayList de traitement à partir du fichier**********************************/

        try {
            ChargementArrayFichier();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*******************************récupération des objets*****************************************************/



        monSpinner = (Spinner)findViewById(R.id.spinnerfct);

        ArrayAdapter fctAdapter=ArrayAdapter.createFromResource(this,
                R.array.tablefct, android.R.layout.simple_spinner_item);

        fctAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        monSpinner.setAdapter(fctAdapter);
        monSpinner.setOnItemSelectedListener(this);


        /***************************************recherche par fct************************************************/

        maListeViewfct = (ListView)findViewById(R.id.listeViewfct);



        bSF= (ImageButton)findViewById(R.id.imageButtonSfct);

        bSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

                for (int i=0;i<ListePourTrait.size();i++) {

                    if (ListePourTrait.get(i).getSexe().equalsIgnoreCase("Femme")){
                        if (ListePourTrait.get(i).getFonction().equalsIgnoreCase(lfctChoisie)){
                            HashMap<String, String> map;
                            map = new HashMap<String, String>();

                            map.put("Nom",ListePourTrait.get(i).getNom());
                            map.put("Prenom",ListePourTrait.get(i).getPrenom());
                            map.put("TypeTravail",ListePourTrait.get(i).getTypeTravail());
                            map.put("Commentaire",ListePourTrait.get(i).getCommentaire());

                            listItem.add(map);

                        }

                    }
                }


                String[] from={"Nom","Prenom","TypeTravail","Commentaire"};//string array
                int[] to ={R.id.nomMembref, R.id.prenomMembref,R.id.typeTravailMembref,R.id.commentMembref};//int array of views id's

                if (listItem.size()==0){
                    Toast.makeText(ListeMembresFeminins.this,"Il y a Aucun membre pour cette fonction",
                            Toast.LENGTH_LONG).show();
                }

                SimpleAdapter monAdapter = new SimpleAdapter(ListeMembresFeminins.this , listItem,
                        R.layout.layout_item_listeviewfct,
                        from,
                        to);

                maListeViewfct.setAdapter(monAdapter);


            }
        });








        /***********************************Bouton retour a l'activité principale*********************************/

        bLF= (ImageButton)findViewById(R.id.imageBListeFeminin);
        bLF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListeMembresFeminins.this.finish();

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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lfctChoisie = monSpinner.getSelectedItem().toString();
        fctChoisie=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
