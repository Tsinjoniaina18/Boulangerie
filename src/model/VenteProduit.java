package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "venteProduit", prefixe = "VENP")
public class VenteProduit {

    @Colonne("id")
    private String id;

    private Date dateVenteProduit;

    private String description;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public Date getDateVenteProduit(){
        return this.dateVenteProduit;
    }

    public void setDateVenteProduit(Date d){
        this.dateVenteProduit = d;
    }

    public void setDateVenteProduit(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateVenteProduit(d);
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }
}
