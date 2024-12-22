package model;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "venteFille", prefixe = "VENPF")
public class VenteFille {

    @Colonne("id")
    private String id;

    private String idVente;

    private String idProduit;

    private double prix;

    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdVente(){
        return this.idVente;
    }

    public void setIdVente(String s){
        this.idVente = s;
    }

    public String getIdProduit(){
        return this.idProduit;
    }

    public void setIdProduit(String s){
        this.idProduit = s;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
    }

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int i){
        this.quantite = i;
    }
}
