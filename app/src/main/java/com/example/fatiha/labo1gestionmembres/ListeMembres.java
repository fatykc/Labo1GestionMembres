package com.example.fatiha.labo1gestionmembres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ListeMembres extends AppCompatActivity {

    TextView listeDesMembres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listermembres);
        String affichage="";
        listeDesMembres= (TextView)findViewById(R.id.resListeMembres);

        Intent leInt= getIntent();

        final ArrayList<Membre> l2Membres = leInt.getParcelableArrayListExtra("LISTETRAIT");

        listeDesMembres.setText(" ");
        affichage=  "Nom"+getString(R.string.tab)+getString(R.string.tab)+getString(R.string.tab)+
                "Prénom"+getString(R.string.tab)+getString(R.string.tab)+
                getString(R.string.tab)+"Sexe"+ getString(R.string.tab)+getString(R.string.tab)+"Fonction"+"\n\n";

        if (l2Membres.size()>0) {

            for (int i = 0; i < l2Membres.size(); i++) {

                affichage += padding_str(l2Membres.get(i).getNom().trim()) + getString(R.string.tab) + getString(R.string.tab)+
                        getString(R.string.tab)+
                        padding_str(l2Membres.get(i).getPrenom().trim())+getString(R.string.tab)+getString(R.string.tab)
                        + getString(R.string.tab) + l2Membres.get(i).getSexe()+getString(R.string.tab)
                        + getString(R.string.tab)
                        +padding_str(l2Membres.get(i).getFonction().trim()) + "\n";


            }


        }

        listeDesMembres.setText(affichage);





        ImageButton b= (ImageButton)findViewById(R.id.imageButtonListe);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent result= new Intent();

                if (l2Membres.size()>0){

                    result.putExtra(MainActivity.REPONSE,"1");
                    setResult(RESULT_OK,result);
                    finish();
                }else
                    result.putExtra(MainActivity.REPONSE,"0");
                    setResult(RESULT_CANCELED,result);
                    finish();



            }
        });

    }

    private static String padding_str(String c){

        if (c.length()<10){
            int nBlanc = 10-c.length();

            for (int j=0;j<nBlanc;j++){
                c+=" ";

            }

        }
        return c;

    }




}
