package model;

import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.NoMap;
import annotation.Table;

@Table(nom = "produit", prefixe = "PROD")
public class Produit {

    @Colonne("id")
    private String id;

    private String nom;

    private String description;

    @Colonne("idcategorie")
    private String idCategorie;

    private double prix;

    @NoMap
    private int stock;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String s){
        this.nom = s;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }

    public String geIdCategorie(){
        return this.idCategorie;
    }

    public void seIdCategorie(String s){
        this.idCategorie = s;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
    }

    public void setPrix(String s)throws Exception{
        double d = Double.parseDouble(s);

        this.setPrix(d);
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int i){
        this.stock = i;
    }
}
