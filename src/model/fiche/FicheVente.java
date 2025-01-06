package model.fiche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import model.VenteFille;
import model.VenteProduit;

public class FicheVente {

    private VenteProduit venteProduit;

    private List<VenteFille> venteFilles;

    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public VenteProduit getVenteProduit() {
        return venteProduit;
    }

    public void setVenteProduit(VenteProduit venteProduit) {
        this.venteProduit = venteProduit;
    }
    
    public List<VenteFille> getVenteFilles() {
        return venteFilles;
    }

    public void setVenteFilles(List<VenteFille> venteFilles) {
        this.venteFilles = venteFilles;
    }

    public void generateFiche (Connection connection, String idVente)throws Exception{

        this.setVenteProduit(GenericRepo.findById(idVente, VenteProduit.class));
        this.venteFilles = new ArrayList<VenteFille>();
        this.categories = new ArrayList<String>();

        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            
            String request = "select * from v_ficheVente where idVente = '"+idVente+"'";
            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();

            while (resultSet.next()) {
                VenteFille venteFille = new VenteFille();
                venteFille.setId(resultSet.getString(1));
                venteFille.setPrix(resultSet.getDouble(4));
                venteFille.setQuantite(resultSet.getInt(5));
                venteFille.setIdProduit(resultSet.getString(6));
                
                this.categories.add(resultSet.getString(7));

                this.venteFilles.add(venteFille);
            }

        } catch (Exception e) {
            throw e;
        } finally{
            if (resultSet!=null) {
                resultSet.close();
            }
            if (prepa!=null) {
                prepa.close();
            }
        }
    }
}
