package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.NoMap;
import annotation.Select;
import annotation.Table;
import connection.PGConnect;

@Table(nom = "produit", prefixe = "PROD")
@Form(actionForm = "produitServlet", methodForm = "post")
public class Produit {

    @Colonne("id")
    private String id;

    @Label(valueLabel = "Nom", forValueLabel = "nom")
    @Input(nameInput = "nom", idInput = "nom")
    private String nom;

    @Label(valueLabel = "Description", forValueLabel = "desc")
    @Input(nameInput = "desc", idInput = "desc")
    private String description;

    @Colonne("idcategorie")
    @Label(valueLabel = "Categorie", forValueLabel = "categorie")
    @Select(nameSelect = "categorie", idSelect = "categorie", reference = "categories", referenceFieldValue = "id", referenceFieldName = "nom")
    private String idCategorie;

    @Label(valueLabel = "Prix", forValueLabel = "prix")
    @Input(typeInput = "number", nameInput = "prix", idInput = "prix")
    private double prix;

    @NoMap
    private int stock;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String s){
        this.nom = s;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }

    public String getIdCategorie(){
        return this.idCategorie;
    }

    public void setIdCategorie(String s){
        this.idCategorie = s;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
    }

    public void setPrix(String s)throws Exception{
        double d = Double.parseDouble(s);

        this.setPrix(d);
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int i){
        this.stock = i;
    }

    public static List<Produit> stockProduit (String id, ArrayList<String> conditions)throws Exception{
        List<Produit> produits = new ArrayList<Produit>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = "select * from v_stockProduit where 1=1";
            if(id!=null){
                request += " and id='"+id+"'";
            }
            if(conditions!=null){
                request = rechercheMultiCritereRequest(request, conditions);
            }
            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                Produit produit = new Produit();
                produit.setId(resultSet.getString(1));
                produit.setNom(resultSet.getString(2));
                produit.setDescription(resultSet.getString(3));
                produit.setIdCategorie(resultSet.getString(7));
                produit.setPrix(resultSet.getString(5));
                produit.setStock(resultSet.getInt(6));

                produits.add(produit);
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
        return produits;
    }

    public static String rechercheMultiCritereRequest(String request, ArrayList<String> conditions){
        String[] starting = new String[6];
        starting[0] = " and Lower(nom) like Lower('%";
        starting[1] = " and idcategorie = '";
        starting[2] = " and prix >= ";
        starting[3] = " and prix < ";
        starting[4] = " and stock >= ";
        starting[5] = " and stock < ";

        String[] ending = new String[2];
        ending[0] = "%')";
        ending[1] = "'";

        for(int i=0; i<conditions.size(); i++){
            if(!conditions.get(i).isEmpty()){
                request += starting[i]+conditions.get(i);
                if(i<2){
                    request += ending[i];
                }
            }
        }

        return request;
    }
}
