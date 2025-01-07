package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import annotation.Colonne;
import annotation.Form;
import annotation.Input;
import annotation.Label;
import annotation.NoMap;
import annotation.Select;
import annotation.Table;
import connection.PGConnect;

@Table(nom = "ingredient", prefixe = "ING")
@Form(actionForm = "ingredientServlet", methodForm = "post")
public class Ingredient {

    @Colonne("id")
    private String id;

    @Label(valueLabel = "Nom", forValueLabel = "nom")
    @Input(nameInput = "nom", idInput = "nom")
    private String nom;

    @Label(valueLabel = "Description", forValueLabel = "desc")
    @Input(nameInput = "desc", idInput = "desc")
    private String description;

    @Label(valueLabel = "Unite", forValueLabel = "unite")
    @Select(nameSelect = "idUnite", idSelect = "unite", reference = "unites", referenceFieldValue = "id", referenceFieldName = "nom")
    @Colonne("idunite")
    private String idUnite;

    @NoMap
    private int stock;

    @NoMap
    private int quantite;

    public String getId(){
        return this.id;
    }

    public void setId(String s){
        this.id = s;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String s){
        this.nom = s;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String s){
        this.description = s;
    }

    public String getIdUnite(){
        return this.idUnite;
    }

    public void setIdUnite(String s){
        this.idUnite = s;
    }

    public int getStock(){  
        return this.stock;
    }

    public void setStock(int i){
        this.stock = i;
    }

    public static List<Ingredient> stockIngredient (String id, ArrayList<String> conditions)throws Exception{
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {
            Connection connection = PGConnect.getInstance().getConnection();
            String request = "select * from v_stockIngredient where 1=1";
            if(id!=null){
                request += " and id='"+id+"'";
            }
            if(conditions!=null){
                request = rechercheMultiCritereRequest(request, conditions);
            }
            System.out.println(request);

            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getString(1));
                ingredient.setNom(resultSet.getString(2));
                ingredient.setDescription(resultSet.getString(3));
                ingredient.setStock(resultSet.getInt(5));

                ingredients.add(ingredient);
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
        return ingredients;
    }

    public static String rechercheMultiCritereRequest(String request, ArrayList<String> conditions){
        String[] starting = new String[3];
        starting[0] = " and stock >= ";
        starting[1] = " and stock < ";
        starting[2] = " and Lower(nom) like Lower('%";

        String ending = "%')";

        for(int i=0; i<conditions.size(); i++){
            if(!conditions.get(i).isEmpty()){
                request += starting[i]+conditions.get(i);
                if(i==conditions.size()-1){
                    request += ending;
                }
            }
        }

        return request;
    }
}
