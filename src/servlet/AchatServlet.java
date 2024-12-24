package servlet;

import java.io.IOException;
import java.util.List;

import connection.PGConnect;
import database.GenericRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Achat;
import model.AchatFille;
import model.Ingredient;
import model.MvtStockIngredient;

@WebServlet(name="AchatServlet", urlPatterns="/achatServlet")
public class AchatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");

        String date = req.getParameter("date");
        String description = req.getParameter("desc");

        String ingredient;
        String quantite;
        String prix;

        PGConnect.getInstance().beginTransaction();
        try {

            Achat achat = new Achat();
            achat.setDateAchat(date);
            achat.setDescription(description);
            achat = GenericRepo.save(achat);

            List<Ingredient> ingredients = GenericRepo.findAll(Ingredient.class);
            for(int i=1; i<=ingredients.size(); i++){
                ingredient = req.getParameter("ingredient-"+i);
                quantite = req.getParameter("quantite-"+i);
                prix = req.getParameter("prix-"+i);

                if(quantite==null || prix == null){
                    break;
                }
                
                AchatFille achatFille = new AchatFille();
                achatFille.setIdAchat(achat.getId());
                achatFille.setIdIngredient(ingredient);
                achatFille.setPrix(prix);
                achatFille.setQuantite(quantite);

                GenericRepo.save(achatFille);

                MvtStockIngredient mvtStockIngredient = new MvtStockIngredient();
                mvtStockIngredient.setIdIngredient(ingredient);
                mvtStockIngredient.setDateMvtI(date);
                mvtStockIngredient.setEntree(quantite);
                mvtStockIngredient.setSortie(0);
                mvtStockIngredient.setPrix(prix);

                GenericRepo.save(mvtStockIngredient);

            } 

            PGConnect.getInstance().commitTransaction();
            
        } catch (Exception e) {
            PGConnect.getInstance().rollbackTransaction();
            e.printStackTrace();
        }
        PGConnect.getInstance().endTransaction();

    }

}