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

    @Label(valueLabel="Date de vente", forValueLabel = "date")
    @Input(typeInput = "date", nameInput = "date", idInput = "date")
    @Colonne("datevente")
    private Date dateVente;

    @Label(valueLabel = "Description", forValueLabel = "desc")
    @Input(nameInput = "desc", idInput = "desc")
    private String description;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public Date getDateVente(){
        return this.dateVente;
    }

    public void setDateVente(Date d){
        this.dateVente = d;
    }

    public void setDateVente(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateVente(d);
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }
}
