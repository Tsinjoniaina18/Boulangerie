package model.fiche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import model.Achat;
import model.AchatFille;

public class FicheAchat {
    
    private Achat achat;
    private List<AchatFille> achatFilles;

    public Achat getAchat(){
        return this.achat;
    }

    public void setAchat(Achat achat){
        this.achat = achat;
    }

    public List<AchatFille> getAchatFilles(){
        return this.achatFilles;
    }

    public void setAchatFilles(List<AchatFille> achatFilles){
        this.achatFilles = achatFilles;
    }

    public void generateFiche(Connection connection, String idAchat)throws Exception{

        this.setAchat(GenericRepo.findById(idAchat, Achat.class));

        this.achatFilles = new ArrayList<AchatFille>();
        PreparedStatement prepa = null;
        ResultSet resultSet = null;
        try {

            String request = "select af.*, ing.nom from achatFille af join ingredient ing on af.idIngredient = ing.id where af.idAchat='"+idAchat+"'";
            prepa = connection.prepareStatement(request);
            resultSet = prepa.executeQuery();
            while(resultSet.next()){
                AchatFille achatFille = new AchatFille();
                achatFille.setId(resultSet.getString(1));
                achatFille.setIdIngredient(resultSet.getString(6));
                achatFille.setPrix(resultSet.getDouble(4));
                achatFille.setQuantite(resultSet.getInt(5));

                this.achatFilles.add(achatFille);
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

    }

}
