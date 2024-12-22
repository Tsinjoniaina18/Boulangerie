package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "categorie", prefixe = "CAT")
public class Categorie {

    @Colonne("id")
    private String id;
    
    private String nom;

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

}
