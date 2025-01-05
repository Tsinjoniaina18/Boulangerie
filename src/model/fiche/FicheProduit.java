package model.fiche;

import java.util.List;

import database.GenericRepo;
import model.Ingredient;
import model.Produit;
import model.Recette;

public class FicheProduit {

    private Produit produit;
    private Ingredient[] ingredients;
    private int[] quantites;

    public Produit getProduit(){
        return this.produit;
    }

    public void setProduit(Produit produit){
        this.produit = produit;
    }

    public Ingredient[] getIngredients(){
        return this.ingredients;
    }

    public int[] getQuantites(){
        return this.quantites;
    }

    public void generateFiche(String idProduit)throws Exception{

        this.setProduit(GenericRepo.findById(idProduit, Produit.class));

        List<Recette> recettes = GenericRepo.findCondition(Recette.class, " and idProduit = '"+this.getProduit().getId()+"'");

        this.ingredients = new Ingredient[recettes.size()];
        this.quantites = new int[recettes.size()];

        for(int i=0; i<recettes.size(); i++){
            this.ingredients[i] = GenericRepo.findById(recettes.get(i).getIdIngredient(), Ingredient.class);
            this.quantites[i] = recettes.get(i).getQuantite();
        }
    }
}
