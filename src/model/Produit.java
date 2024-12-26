package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.NoMap;
import annotation.Table;
import connection.PGConnect;

@Table(nom = "produit", prefixe = "PROD")
public class Produit {

    @Colonne("id")
    private String id;

    private String nom;

    private String description;

    @Colonne("idcategorie")
    private String idCategorie;

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

    public static List<Produit> stockProduit (String id)throws Exception{
        List<Produit> produits = new ArrayList<Produit>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = "select * from v_stockProduit where 1=1";
            if(id!=null){
                request += " and id='"+id+"'";
            }
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
}
