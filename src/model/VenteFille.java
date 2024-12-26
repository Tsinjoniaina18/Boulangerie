package model;

import java.util.ArrayList;
import java.util.Map;

import annotation.Colonne;
import annotation.Table;
import database.GenericRepo;

@Table(nom = "ventefille", prefixe = "VENPF")
public class VenteFille {

    @Colonne("id")
    private String id;

    @Colonne("idvente")
    private String idVente;

    @Colonne("idproduit")
    private String idProduit;

    private double prix;

    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdVente(){
        return this.idVente;
    }

    public void setIdVente(String s){
        this.idVente = s;
    }

    public String getIdProduit(){
        return this.idProduit;
    }

    public void setIdProduit(String s){
        this.idProduit = s;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
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

    public static void venteProduit (String idVente, Map<String, ArrayList> map)throws Exception{
        
        ArrayList<String> produits = map.get("produits");
        ArrayList<String> quantites = map.get("quantites");

        Produit produit;
        for(int i=0; i<produits.size(); i++){
            
            produit = GenericRepo.findById(produits.get(i), Produit.class);

            VenteFille venteFille = new VenteFille();
            venteFille.setIdVente(idVente);
            venteFille.setIdProduit(produits.get(i));
            venteFille.setPrix(produit.getPrix());
            venteFille.setQuantite(quantites.get(i));

            GenericRepo.save(venteFille);
        }

    }
}
