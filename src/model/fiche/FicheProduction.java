package model.fiche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import model.Production;
import model.ProductionFille;

public class FicheProduction {

    private Production production;

    private List<ProductionFille> productionFilles;

    private List<String> categories;

    public List<ProductionFille> getProductionFilles() {
        return productionFilles;
    }

    public void setProductionFilles(List<ProductionFille> productionFilles) {
        this.productionFilles = productionFilles;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    

    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void generateFiche(Connection connection, String idProduction)throws Exception
    {

        this.setProduction(GenericRepo.findById(idProduction, Production.class));
        this.productionFilles = new ArrayList<ProductionFille>();
        this.categories = new ArrayList<String>();

        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            
            String request = "select * from v_ficheProduction where idProduction = '"+idProduction+"'";
            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while (resultSet.next()) {
                ProductionFille productionFille = new ProductionFille();
                productionFille.setId(resultSet.getString(1));
                productionFille.setQuantite(resultSet.getInt(4));
                productionFille.setIdProduit(resultSet.getString(5));

                this.categories.add(resultSet.getString(6));

                this.productionFilles.add(productionFille);
            }

        } catch (Exception e) {
            throw e;
        } finally{
            if (resultSet!=null) {
                resultSet.close();
            }
            if(prepa!=null){
                prepa.close();
            }
        }

    }
}
