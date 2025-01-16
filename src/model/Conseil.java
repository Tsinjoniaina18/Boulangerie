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
import annotation.Select;
import annotation.Table;
import connection.PGConnect;

@Form(actionForm = "conseilServlet", methodForm = "post")
@Table(nom = "conseil", prefixe = "CONS")
public class Conseil {

    @Colonne("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Label(valueLabel = "Produit", forValueLabel = "produit")
    @Select(nameSelect = "produit", reference = "produit", referenceFieldValue = "id", referenceFieldName = "nom", idSelect = "produit")
    @Colonne("idproduit")
    private String idProduit;

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Label(valueLabel = "Mois", forValueLabel = "mois")
    @Select(nameSelect = "mois", reference = "mois", referenceFieldValue = "id", referenceFieldName = "nom", idSelect = "mois")
    @Colonne("mois")
    private int mois;

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setMois(String s) throws Exception{
        int m = Integer.parseInt(s);
        this.setMois(m);
    }

    @Label(valueLabel = "Annee", forValueLabel = "annee")
    @Input(nameInput = "annee", typeInput = "number", idInput = "annee")
    @Colonne("annee")
    private int annee;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setAnnee(String s) throws Exception{
        int m = Integer.parseInt(s);
        this.setAnnee(m);
    } 
    
    public static List<Conseil> rechercherConseils(String mois, String annee)throws Exception{
        List<Conseil> conseils = new ArrayList<Conseil>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = " select c.*, p.nom from conseil c join produit p on c.idProduit = p.id where 1=1";
            if(mois!=null){
                request +=  " and mois = " + mois + " and annee = "+annee;
            }
            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                Conseil conseil = new Conseil();
                conseil.setId(resultSet.getString(1));
                conseil.setIdProduit(resultSet.getString(5));
                conseil.setMois(resultSet.getInt(3));
                conseil.setAnnee(resultSet.getInt(4));

                conseils.add(conseil);
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
        return conseils;
    }
}
