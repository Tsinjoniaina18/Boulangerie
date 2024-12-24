package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "production", prefixe = "PROD")
public class Production {

    @Colonne("id")
    private String id;

    @Colonne("dateproduction")
    private Date dateProduction;

    private String description;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public Date getDateProduction(){
        return this.dateProduction;
    }

    public void setDateProduction(Date d){
        this.dateProduction = d;
    }

    public void setDateProduction(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateProduction(d);
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }
}
