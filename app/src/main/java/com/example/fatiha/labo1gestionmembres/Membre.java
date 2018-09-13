package com.example.fatiha.labo1gestionmembres;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fatiha on 2018-02-19.
 */

public class Membre implements Parcelable {

    private String nom;
    private String prenom;
    private String sexe;
    private String fonction;
    private String typeTravail;
    private String commentaire;

    public static final Parcelable.Creator<Membre> CREATOR= new Parcelable.Creator<Membre>() {


        @Override
        public Membre createFromParcel(Parcel source) {

            Membre leMembre = new Membre(source.readString(),source.readString(),source.readString(),source.readString(),
                               source.readString(),source.readString());

            return leMembre;
        }


        @Override
        public Membre[] newArray(int size) {
            return new Membre[size];
        }
    };

    public Membre(){ };


    public Membre(String nom, String prenom, String sexe, String fonction,String typeTravail, String commentaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.fonction = fonction;
        this.typeTravail=typeTravail;
        this.commentaire = commentaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getTypeTravail() {
        return typeTravail;
    }

    public void setTypeTravail(String typeTravail) {
        this.typeTravail = typeTravail;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(sexe);
        dest.writeString(fonction);
        dest.writeString(typeTravail);
        dest.writeString(commentaire);




    }
}
