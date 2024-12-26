package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import annotation.Colonne;
import annotation.Table;
import database.GenericRepo;

@Table(nom = "mvtstockproduit", prefixe = "MVTP")
public class MvtStockProduit {

    @Colonne("id")
    private String id;

    @Colonne("idproduit")
    private String idProduit;

    @Colonne("datemvtp")
    private Date dateMvtP;

    private int entree;

    private int sortie;

    private double prix;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getIdProduit(){
        return this.idProduit;
    }

    public void setIdProduit(String s){
        this.idProduit = s;
    }

    public Date getDateMvtP(){
        return this.dateMvtP;
    }

    public void setDateMvtP(Date d){
        this.dateMvtP = d;
    }

    public void setDateMvtP(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateMvtP(d);
    }

    public int getEntree(){
        return this.entree;
    }

    public void setEntree(int i){
        this.entree = i;
    }

    public void setEntree(String s)throws Exception{
        int i = Integer.parseInt(s);
        this.setEntree(i);
    }

    public int getSortie(){
        return this.sortie;
    }

    public void setSortie(int i){
        this.sortie = i;
    }

    public void setSortie(String s)throws Exception{
        int i = Integer.parseInt(s);
        this.setSortie(i);
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double d){
        this.prix = d;
    }

    public static void produitProduction(String date, Map<String, ArrayList> map)throws Exception{

        ArrayList<String> produits = map.get("produits");
        ArrayList<String> quantites = map.get("quantites");

        for(int i=0; i<produits.size(); i++){
            MvtStockProduit mvtStockProduit = new MvtStockProduit();
            mvtStockProduit.setIdProduit(produits.get(i));
            mvtStockProduit.setDateMvtP(date);
            mvtStockProduit.setEntree(quantites.get(i));
            mvtStockProduit.setSortie(0);
            mvtStockProduit.setPrix(0);
            
            GenericRepo.save(mvtStockProduit);
        }
    }

    public static void venteProduit(String date, Map<String, ArrayList> map)throws Exception{
        
        ArrayList<String> produits = map.get("produits");
        ArrayList<String> quantites = map.get("quantites");

        int besoin;
        int avoir;
        Produit produit;
        for(int i=0; i<produits.size(); i++){
            besoin = Integer.parseInt(quantites.get(i));
            avoir = Produit.stockProduit(produits.get(i)).get(0).getStock();

            if(avoir < besoin){
                throw new Exception("Produits insuffisant pour la vente");
            }

            produit = GenericRepo.findById(produits.get(i), Produit.class);

            MvtStockProduit mvtStockProduit = new MvtStockProduit();
            mvtStockProduit.setIdProduit(produits.get(i));
            mvtStockProduit.setDateMvtP(date);
            mvtStockProduit.setEntree(0);
            mvtStockProduit.setSortie(quantites.get(i));
            mvtStockProduit.setPrix(produit.getPrix());

            GenericRepo.save(mvtStockProduit);
        }
    }
}