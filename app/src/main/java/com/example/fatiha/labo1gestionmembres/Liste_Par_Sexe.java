package com.example.fatiha.labo1gestionmembres;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Liste_Par_Sexe extends AppCompatActivity {
    RadioGroup Rgp;
    RadioButton radioSexe;
    ListView maListeView;

    BufferedReader ficEntree=null;
    String fichier= "membres.txt";
    ArrayList<Membre> ListePourTrait= new ArrayList<Membre>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_par_sexe);

        try {
            ChargementArrayFichier();
        } catch (IOException e) {
            e.printStackTrace();
        }




        Rgp= (RadioGroup)findViewById(R.id.groupR);
        ImageButton bs=(ImageButton) findViewById(R.id.imageButtonSearch);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId;

                selectedId = Rgp.getCheckedRadioButtonId();
                radioSexe = (RadioButton) findViewById(selectedId);
                maListeView= (ListView)findViewById(R.id.maListeView);


                ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

                for (int i=0;i<ListePourTrait.size();i++) {

                   if (ListePourTrait.get(i).getSexe().equalsIgnoreCase(radioSexe.getText().toString())){
                        HashMap<String, String> map;
                        map = new HashMap<String, String>();

                        map.put("Nom",ListePourTrait.get(i).getNom());
                        map.put("Prenom",ListePourTrait.get(i).getPrenom());
                        map.put("Fonction",ListePourTrait.get(i).getFonction());
                        map.put("TypeTravail",ListePourTrait.get(i).getTypeTravail());
                        map.put("Commentaire",ListePourTrait.get(i).getCommentaire());

                        listItem.add(map);

                     }

                }
                String[] from={"Nom","Prenom"};//string array
                int[] to ={R.id.nomMembre, R.id.prenomMembre};//int array of views id's

                if (listItem.size()==0){
                    Toast.makeText(Liste_Par_Sexe.this,"Il y a Aucun membre "+ radioSexe.getText().toString(),
                            Toast.LENGTH_LONG).show();
                }

                SimpleAdapter monAdapter = new SimpleAdapter(Liste_Par_Sexe.this , listItem,
                        R.layout.layout_item_listview,
                        from,
                       to);

                maListeView.setAdapter(monAdapter);

                maListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        HashMap<String, String> map = (HashMap<String, String>)
                                maListeView.getItemAtPosition(position);
                        //on créer une boite de dialogue
                        AlertDialog.Builder adb = new AlertDialog.Builder(Liste_Par_Sexe.this);
                        //on attribut un titre à notre boite de dialogue
                        adb.setTitle("Le Membre:"+map.get("Nom")+ " "+ map.get("Prenom") );
                        //on insère un message à notre boite de dialogue, et ici on affiche le titre de
                        //l'item cliqué
                        adb.setMessage( "Fonction :"+ map.get("Fonction")+"\n"+
                                        "Type de travail : "+map.get("TypeTravail") +"\n"+
                                        "Commentaire : "+map.get("Commentaire"));
                        //on indique que l'on veut le bouton ok à notre boite de dialogue
                        adb.setPositiveButton("Ok", null);
                        //on affiche la boite de dialogue
                        adb.show();

                    }
                });


            }
        });





        ImageButton b1= (ImageButton)findViewById(R.id.imageBListePSexe);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Liste_Par_Sexe.this.finish();


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
