package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.Table;
import connection.PGConnect;

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

     public static List<Production> listProduction (ArrayList<String> conditions)throws Exception{
        List<Production> productions = new ArrayList<Production>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = "select * from v_rechercheProduction where 1=1";

            request = requestConditions(request, conditions);

            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                Production production = new Production();
                production.setId(resultSet.getString(1));
                production.setDateProduction(resultSet.getString(2));
                production.setDescription(resultSet.getString(3));
               
                productions.add(production);
            }
        } catch (Exception e) {
            throw e;
        } finally{
            if(resultSet!=null){
                resultSet.close();
            }
            if(prepa!=null){
                prepa.close();
            }
        }
        return productions;
    }

    public static String requestConditions(String request, ArrayList<String> conditions)throws Exception{
        String[] starting = new String[2];
        starting[0] = " and idCategorie = '";
        starting[1] = " and idIngredient = '";
        String ending = "'";
        for(int i=0; i<conditions.size(); i++){
            if(!conditions.get(i).isEmpty()){
                request += starting[i]+conditions.get(i)+ending;       
            }
        }
        return request;
    }

}
