package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "mvtStockIngredient", prefixe = "MVTI")
public class MvtStockIngredient {

    @Colonne("id")
    private String id;

    private String idIngredient;

    private Date dateMvtI;

    private int entree;

    private int sortie;

    private double prix;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdIngredient(){
        return this.idIngredient;
    }

    public void setIdIngredient(String s){
        this.idIngredient = s;
    }

    public Date getDateMvtI(){
        return this.dateMvtI;
    }

    public void setDateMvtI(Date d){
        this.dateMvtI = d;
    }

    public void setDateMvtI(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateMvtI(d);
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
