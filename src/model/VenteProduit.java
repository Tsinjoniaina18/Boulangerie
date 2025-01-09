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

    public static List<VenteProduit> listVente (ArrayList<String> conditions)throws Exception{
        List<VenteProduit> venteProduits = new ArrayList<VenteProduit>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = "select * from v_venteNature where 1=1";

            request = requestConditions(request, conditions);

            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                VenteProduit venteProduit = new VenteProduit();
                venteProduit.setId(resultSet.getString(1));
                venteProduit.setDateVente(resultSet.getString(2));
                venteProduit.setDescription(resultSet.getString(3));
               
                venteProduits.add(venteProduit);
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
        return venteProduits;
    }

    public static String requestConditions(String request, ArrayList<String> conditions)throws Exception{
        String[] starting = new String[2];
        starting[0] = " and idCategorie = '";
        starting[1] = " and estNature = ";
        String ending = "'";
        for(int i=0; i<conditions.size(); i++){
            if(!conditions.get(i).isEmpty()){
                request += starting[i]+conditions.get(i);
                if(i==0){
                    request+= ending;
                }       
            }
        }
        return request;
    }
}
