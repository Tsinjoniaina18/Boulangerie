package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "mvtstockproduit", prefixe = "MVTP")
public class MvtStockProduit {

    @Colonne("id")
    private String id;

    @Colonne("idproduit")
    private String idProduit;

    @Colonne("datemvtp")
    private Date dateMvtP;

    private int entree;

    private int sortie;

    private double prix;

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

    public Date getDateMvtP(){
        return this.dateMvtP;
    }

    public void setDateMvtP(Date d){
        this.dateMvtP = d;
    }

    public void setDateMvtP(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateMvtP(d);
    }

    public int getEntree(){
        return this.entree;
    }

    public void setEntree(int i){
        this.entree = i;
    }

    public int getSortie(){
        return this.sortie;
    }

    public void setSortie(int i){
        this.sortie = i;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
    }
}