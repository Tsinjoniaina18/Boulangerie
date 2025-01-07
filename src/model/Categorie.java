package model;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;

@Table(nom = "categorie", prefixe = "CAT")
@Form(actionForm = "/Boulangerie/categorieServlet", methodForm = "post")
public class Categorie {

    @Colonne("id")
    private String id;
    
    @Label(valueLabel = "Nom",forValueLabel = "nom")
    @Input(nameInput = "nom",idInput = "nom")
   
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
