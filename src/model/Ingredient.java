package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "ingredient", prefixe = "ING")
public class Ingredient {

    @Colonne("id")
    private String id;

    private String nom;

    private String description;

    private double prix;

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
}
