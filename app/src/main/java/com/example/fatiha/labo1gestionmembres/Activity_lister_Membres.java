package com.example.fatiha.labo1gestionmembres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.fatiha.labo1gestionmembres.R.id.imageListeMembres;

/**
 * Created by Fatiha on 2018-02-26.
 */

public class Activity_lister_Membres extends AppCompatActivity {
    ListView listeViewMembres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_membres);








        Intent leInt = getIntent();

        final ArrayList<Membre> l2Membres = leInt.getParcelableArrayListExtra("LISTETRAIT");

        listeViewMembres = (ListView) findViewById(R.id.listeViewmbres);
        ArrayList<HashMap<String, String>> listeItem = new ArrayList<HashMap<String, String>>();

        if (l2Membres.size() > 0) {



            for (int i = 0; i < l2Membres.size(); i++) {

                HashMap<String, String> map;
                map = new HashMap<String, String>();

                map.put("Nom", l2Membres.get(i).getNom());
                map.put("Prenom", l2Membres.get(i).getPrenom());
                map.put("Sexe", l2Membres.get(i).getSexe());
                map.put("Fonction", l2Membres.get(i).getFonction());

                listeItem.add(map);
            }


        }
        String[] from = {"Nom", "Prenom", "Sexe", "Fonction"};//string array
        int[] to = {R.id.nomMembre, R.id.prenomMembre, R.id.sexeMembre, R.id.fctMembre};//int array of views id's


        if (listeItem.size() == 0) {
            Toast.makeText(Activity_lister_Membres.this, "Il y a Aucun membre  ",
                    Toast.LENGTH_LONG).show();
        }

        SimpleAdapter monAdapter = new SimpleAdapter(Activity_lister_Membres.this, listeItem,
                R.layout.layout_item_listeviewmembres,
                from,
                to);
        listeViewMembres.setAdapter(monAdapter);



    ImageButton br= (ImageButton)findViewById(imageListeMembres);
        br.setOnClickListener(new View.OnClickListener() {
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


}