package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.Input;
import annotation.Label;
import annotation.NoMap;
import annotation.Table;
import connection.PGConnect;

@Table(nom = "vendeur", prefixe = "VEND")
public class Vendeur {

    @Colonne("id")
    private String id;

    @Label(valueLabel = "Nom", forValueLabel = "nom")
    @Input(nameInput = "nom", idInput = "nom")
    @Colonne("nom")
    private String nom;

    @Label(valueLabel = "Date Embauche", forValueLabel = "date")
    @Input(nameInput = "date", typeInput = "date", idInput = "date")
    @Colonne("dateembauche")
    private Date dateEmbauche;

    @NoMap
    private double commission;
    
    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    
    public static List<Vendeur> commissionVendeur (double pourcentage, String debut, String fin)throws Exception{
        List<Vendeur> vendeurs = new ArrayList<Vendeur>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();

            String condition = "";
            if (!debut.equals("")) {
                condition += "  and v.datevente > '"+debut+"'";
            }
            if (!fin.equals("")) {
                condition += "  and v.datevente <= '"+fin+"'";
            }

            String request = "select vend.*, sum(vf.prix*vf.quantite)*"+pourcentage/100+" as commission from venteFille vf join venteProduit v on vf.idVente = v.id join vendeur vend on v.idVendeur = vend.id where 1=1"+condition+" group by vend.id";

            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                Vendeur vendeur = new Vendeur();
                vendeur.setId(resultSet.getString(1));
                vendeur.setNom(resultSet.getString(2));
                vendeur.setDateEmbauche(resultSet.getDate(3));
                vendeur.setCommission(resultSet.getDouble(4));

                vendeurs.add(vendeur);
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
        return vendeurs;
    }
}
