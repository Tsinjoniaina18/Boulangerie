package model;

import annotation.Colonne;
import annotation.Table;
import database.GenericRepo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Table(nom = "historiqueprix", prefixe = "HISTOP")
public class HistoriqueProduit {
    @Colonne("id")
    private String id;

    @Colonne("idproduit")
    private String idProduit;

    @Colonne("datechangement")
    private Date dateChangement;

    @Colonne("prix")
    private double prix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public Date getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }

    public void setDateChangement(String s)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date(sdf.parse(s).getTime());

        this.setDateChangement(d);
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public  void setPrix(String s)throws  Exception{
        this.setPrix(Double.parseDouble(s));
    }

    public List<HistoriqueProduit> findHistoriquePrix(String idProduit) throws Exception {
        String afterWhere = "";

        if(!idProduit.equals("")){
            afterWhere += " and idProduit = '"+idProduit+"'";
        }

        System.out.println("Where: "+afterWhere);

        List<HistoriqueProduit> historiqueProduits = GenericRepo.findCondition(HistoriqueProduit.class, afterWhere);

        for (int i = 0; i < historiqueProduits.size(); i++) {
            Produit produit = GenericRepo.findById(historiqueProduits.get(i).getIdProduit(), Produit.class);
            historiqueProduits.get(i).setIdProduit(produit.getNom());
        }
        return historiqueProduits;
    }
}
