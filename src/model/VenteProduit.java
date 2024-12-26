package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;

@Form(actionForm = "venteServlet", methodForm = "post")
@Table(nom = "venteproduit", prefixe = "VENP")
public class VenteProduit {

    @Colonne("id")
    private String id;

    @Colonne("datevente")
    @Label(valueLabel="Date de vente", forValueLabel = "date")
    @Input(typeInput = "date", nameInput = "date", idInput = "date")
    private Date dateVenteProduit;

    @Label(valueLabel = "Description", forValueLabel = "desc")
    @Input(nameInput = "desc", idInput = "desc")
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
