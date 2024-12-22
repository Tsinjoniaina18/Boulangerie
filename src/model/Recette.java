package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "recette", prefixe = "REC")
public class Recette {

    @Colonne("id")
    private String id;

    private String idProduit;

    private String idIngredient;

    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdProduit(){
        return this.idProduit;
    }

    public void setIdProduit(String s){
        this.idProduit = s;
    }

    public String getIdIngredient(){
        return this.idIngredient;
    }

    public void setIdIngredient(String s){
        this.idIngredient = s;
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
