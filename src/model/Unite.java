package model;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;

@Table(nom = "unite", prefixe = "UNI")
@Form(actionForm = "/Boulangerie/uniteServlet", methodForm = "post")
public class Unite {
    
   @Colonne("id")
    private String id;
    
    @Label(valueLabel = "Nom", forValueLabel = "nom")
    @Input(nameInput = "nom", idInput = "nom")
    private String nom;

    @Label(valueLabel = "Initiale", forValueLabel = "initiale")
    @Input(nameInput = "initiale", idInput = "initiale")
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
