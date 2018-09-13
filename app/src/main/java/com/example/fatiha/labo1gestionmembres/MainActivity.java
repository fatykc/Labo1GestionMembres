package com.example.fatiha.labo1gestionmembres;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    public final static String LISTE="com.example.fatiha.labo1gestionmembres.LISTE";
    public final static String LISTETRAIT="com.example.fatiha.labo1gestionmembres.LISTETRAIT";

    public final static int CODE1=1;
    public final static int CODE2=2;
    public final static int CODE3=3;
    EditText Enom,Eprenom,EComm;
    RadioGroup Rgp;
    RadioButton radioSexe;
    CheckBox Cjour,Ctpar,CtPlein,CtOccas;
    int selectedId;
    String item;
    BufferedReader ficEntree=null;
    String fichier= "membres.txt";


    ArrayList<Membre>  listeMembres= new ArrayList<Membre>();
    ArrayList<Membre>  ListePourTrait= new ArrayList<Membre>();
    public final static int CODE=0;
    public final static String REPONSE= "com.example.fatiha.labo1gestionmembres.REPONSE";
    private TextView texte;
    private String resulat="";


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.ajouter){
            creationDialogueMembre();
            return true;

        }
        if (item.getItemId()==R.id.enregistrer){
            Intent i = new Intent(MainActivity.this, EnregistrementdansFichier.class);
            i.putParcelableArrayListExtra("LISTE",listeMembres);

            startActivity(i);
            listeMembres.clear();
            return true;
        }

        if (item.getItemId()==R.id.lister){
            try {
                ChargementArrayFichier();
            } catch (IOException e) {
                e.printStackTrace();
            }
           // Intent ilister= new Intent(MainActivity.this,ListeMembres.class);
            Intent ilister= new Intent(MainActivity.this,Activity_lister_Membres.class);
            ilister.putParcelableArrayListExtra("LISTETRAIT",ListePourTrait);
            startActivityForResult(ilister,CODE2);
            ListePourTrait.clear();
            return true;

        }

        if (item.getItemId()==R.id.rechercher) {


            Intent iRecherche = new Intent(MainActivity.this,ChercherUnMembre.class);
           // startActivityForResult(iRecherche,CODE3);

           startActivity(iRecherche);

            return true;

        }


        if (item.getItemId()==R.id.listePSexe){



            Intent iListepSexe = new Intent(MainActivity.this,Liste_Par_Sexe.class);
            startActivity(iListepSexe);
            return true;
        }

        if (item.getItemId()==R.id.listeffct){

            Intent iListeFFct= new Intent(MainActivity.this,ListeMembresFeminins.class);
            startActivity(iListeFFct);
            return true;



        }



        return super.onOptionsItemSelected(item);
    }

    private void startActivityForResult(Intent ilister) {
    }

    private void creationDialogueMembre() {

        final Dialog membre = new Dialog(this);

        membre.setContentView(R.layout.ajoutermembre);
        Button BEffacer,BEnvoyer;

        Spinner spner = (Spinner)membre.findViewById(R.id.spinner1);

        spner.setOnItemSelectedListener(this);

        List<String> fctList =new ArrayList<String>();
        fctList.add("Etudiant");
        fctList.add("Enseignant");
        fctList.add("Ingénieur");
        fctList.add("Retraité");
        fctList.add("Autre");

        ArrayAdapter<String> fctAdapter=new
                ArrayAdapter<String>(this ,android.R.layout.simple_spinner_item,fctList);
        spner.setAdapter(fctAdapter);

        Enom=(EditText) membre.findViewById(R.id.Snom);
        Eprenom = (EditText) membre.findViewById(R.id.Sprenom);
        EComm= (EditText)membre.findViewById(R.id.SComm);
        Rgp=(RadioGroup) membre.findViewById(R.id.groupR);
        Cjour=(CheckBox) membre.findViewById(R.id.tjour);
        Ctpar=(CheckBox) membre.findViewById(R.id.tpart);
        CtPlein= (CheckBox) membre.findViewById(R.id.tplein);
        CtOccas= (CheckBox) membre.findViewById(R.id.toccas);


        Rgp.setOnClickListener(this);
        Cjour.setOnClickListener(this);
        Ctpar.setOnClickListener(this);
        CtPlein.setOnClickListener(this);
        CtOccas.setOnClickListener(this);

        /**************************************Gestion Boutton envoyer*****************************************/
        BEnvoyer=(Button) membre.findViewById(R.id.btEnv);
        BEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String typedetravail="";
                selectedId = Rgp.getCheckedRadioButtonId();

                radioSexe = (RadioButton) membre.findViewById(selectedId);


                if (Cjour.isChecked()) {
                    typedetravail += Cjour.getText().toString() + " ";
                }
                if (Ctpar.isChecked()) {
                    typedetravail += Ctpar.getText().toString() + " " ;
                }

                if (CtPlein.isChecked()) {
                    typedetravail += CtPlein.getText().toString() + " ";
                }
                if (CtOccas.isChecked()) {
                    typedetravail += CtOccas.getText().toString() + " ";
                }




                Toast.makeText(MainActivity.this, Enom.getText().toString() + "\n" + Eprenom.getText().toString()
                        + "\n" + radioSexe.getText().toString() + "\n" + typedetravail +"\n" + EComm.getText().toString()+"\n"
                        + item,LENGTH_LONG).show();

                if(typedetravail.trim().length()==0){
                    typedetravail="NR";
                }
                if (item.trim().length()==0) {
                    item="NR";
                }
                String com=EComm.getText().toString();
                if (com.trim().length()==0){
                    com="NR";
                }

                listeMembres.add(new Membre(Enom.getText().toString(), Eprenom.getText().toString(),
                                    radioSexe.getText().toString(),item,typedetravail,com));
               Toast.makeText(MainActivity.this, "Membre enregistré",Toast.LENGTH_SHORT).show();


            }
        });


        /************************************Gestion boutton Effacer********************************************/

        BEffacer=(Button) membre.findViewById(R.id.btEfface);

        BEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enom.getText().clear();
                Eprenom.getText().clear();
                EComm.getText().clear();

                Rgp.check(R.id.bRadio2);
                //*************************Remise a blanc des checked box**************************************/
                if (Cjour.isChecked()) {
                    Cjour.setChecked(false);
                }

                if (Ctpar.isChecked()) {
                    Ctpar.setChecked(false);
                }
                if (CtPlein.isChecked()) {
                    CtPlein.setChecked(false);

                }

                if (CtOccas.isChecked()) {
                    CtOccas.setChecked(false);
                }


            }
        });




        /******************************Gestion boutton Quitter***********************************/




        ImageButton btRetour = (ImageButton) membre.findViewById(R.id.bretour);
        //btQuitter.setOnClickListener(this);

        btRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retour � l'activit�
                membre.dismiss();
            }


        });

        // Rendre dialog visible.
        membre.show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }

   public void ChargementArrayFichier()throws IOException {

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
