package model;

import java.sql.Date;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.NoMap;
import annotation.Table;

@Form(actionForm = "/Boulangerie/templateServlet", methodForm = "post")
@Table(nom = "template", prefixe = "TEMP")
public class Template {

    @Colonne("id")
    private String id;

    @Label(valueLabel = "Texte:")
    @Input(nameInput = "texte", placeholderInput = "texte")
    private String texte;

    @Label(valueLabel = "Nombre:")
    @Input(nameInput = "nombre", typeInput = "number")
    private int nombre;

    @Label(valueLabel = "Date:")
    @Input(nameInput = "date", typeInput = "date")
    private Date date;

    @NoMap
    private String pasColonne;

    public String getTexte(){
        return this.texte;
    }

    public void setTexte(String s){
        this.texte = s;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int i) {
        this.nombre = i;
    }

    public void setNombre(String s)throws Exception{
        int i= Integer.parseInt(s);
        this.setNombre(i);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasColonne(){
        return this.pasColonne;
    }

    public void setPasColonne(String s){
        this.pasColonne = s;
    }
    
}
