package model;

import annotation.Colonne;
import annotation.Table;

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

}
