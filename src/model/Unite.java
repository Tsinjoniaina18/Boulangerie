package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "unite", prefixe = "UNI")
public class Unite {
    
   @Colonne("id")
    private String id;
    
    private String nom;

    private String initiale;

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
    
    public String getInitiale(){
        return this.initiale;
    }

    public void setInitiale(String s){
        this.initiale = s;
    }

}
