package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;

@Table(nom = "production", prefixe = "PROD")
@Form(actionForm = "productionServlet", methodForm = "post")
public class Production {

    @Colonne("id")
    private String id;

    @Colonne("dateproduction")
    @Label(valueLabel = "Date de Production", forValueLabel = "date")
    @Input(nameInput = "date", typeInput = "date", idInput = "date")
    private Date dateProduction;

    @Label(valueLabel = "Description", forValueLabel = "desc")
    @Input(nameInput = "desc", idInput = "desc")
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
