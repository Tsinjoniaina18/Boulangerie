package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Table;

@Table(nom = "achat", prefixe = "ACH")
public class Achat {

    @Colonne("id")
    private String id;

    private Date dateAchat;

    private String description;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public Date getDateAchat(){
        return this.dateAchat;
    }

    public void setDateAchat(Date d){
        this.dateAchat = d;
    }

    public void setDateAchat(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateAchat(d);
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }
}
