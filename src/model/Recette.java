package model;

import java.util.ArrayList;
import java.util.Map;

import annotation.Colonne;
import annotation.Table;
import database.GenericRepo;

@Table(nom = "recette", prefixe = "REC")
public class Recette {

    @Colonne("id")
    private String id;

    @Colonne("idproduit")
    private String idProduit;

    @Colonne("idingredient")
    private String idIngredient;

    private int quantite;

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

    public String getIdIngredient(){
        return this.idIngredient;
    }

    public void setIdIngredient(String s){
        this.idIngredient = s;
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

    public static void generateProduit(String id, Map<String, ArrayList> map)throws Exception{
        ArrayList<String> ingredients = map.get("ingredients");
        ArrayList<String> quantites = map.get("quantites");

        for(int i=0; i<ingredients.size(); i++){

            System.out.println("ingredient: "+ingredients.get(i));

            Recette recette = new Recette();
            recette.setIdProduit(id);
            recette.setIdIngredient(ingredients.get(i));
            recette.setQuantite(quantites.get(i));

            GenericRepo.save(recette);
        }
    }
}
