package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;

@Table(nom = "achat", prefixe = "ACH")
@Form(actionForm = "achatServlet", methodForm = "post")
public class Achat {

    @Colonne("id")
    private String id;

    @Label(valueLabel = "Date Achat", forValueLabel = "date")
    @Input(typeInput = "date",nameInput = "date" ,idInput = "date")
    @Colonne("dateachat")
    private Date dateAchat;

    @Label(valueLabel = "Description", forValueLabel = "description")
    @Input(nameInput = "desc" ,idInput = "description")
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
