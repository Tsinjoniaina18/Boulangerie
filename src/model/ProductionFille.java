package model;

import java.util.ArrayList;
import java.util.Map;

import annotation.Colonne;
import annotation.Table;
import database.GenericRepo;

@Table(nom = "productionfille", prefixe = "PRODF")
public class ProductionFille {
    
    @Colonne("id")
    private String id;

    @Colonne("idproduction")
    private String idProduction;

    @Colonne("idproduit")
    private String idProduit;

    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdProduction(){
        return this.idProduction;
    }

    public void setIdProduction(String s){
        this.idProduction = s;
    }

    public String getIdProduit(){
        return this.idProduit;
    }

    public void setIdProduit(String s){
        this.idProduit = s;
    }

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int i){
        this.quantite = i;
    }

    public void setQuantite(String s)throws Exception{
        int i = Integer.parseInt(s);
        this.setQuantite(i);
    }

    public static void productionInsert(String idProduction, Map<String, ArrayList> map)throws Exception{

        ArrayList<String> produits = map.get("produits");
        ArrayList<String> quantites = map.get("quantites");

        for(int i=0; i<produits.size(); i++){

            ProductionFille productionFille = new ProductionFille();
            productionFille.setIdProduction(idProduction);
            productionFille.setIdProduit(produits.get(i));
            productionFille.setQuantite(quantites.get(i));

            GenericRepo.save(productionFille);
        }
    }
}
