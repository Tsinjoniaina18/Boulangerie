package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "achatfille", prefixe = "ACHF")
public class AchatFille {

    @Colonne("id")
    private String id;

    @Colonne("idachat")
    private String idAchat;

    @Colonne("idingredient")
    private String idIngredient;

    private double prix;

    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdAchat(){
        return this.idAchat;
    }

    public void setIdAchat(String s){
        this.idAchat = s;
    }

    public String getIdIngredient(){
        return this.idIngredient;
    }

    public void setIdIngredient(String s){
        this.idIngredient = s;
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

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int i){
        this.quantite = i;
    }

    public void setQuantite(String s)throws Exception{
        int i = Integer.parseInt(s);
        this.setQuantite(i); 
    }
}
