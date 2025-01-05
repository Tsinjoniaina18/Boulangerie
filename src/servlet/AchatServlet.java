package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import connection.PGConnect;
import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Achat;
import model.AchatFille;
import model.Ingredient;
import model.MvtStockIngredient;
import model.fiche.FicheAchat;

@WebServlet(name="AchatServlet", urlPatterns="/achatServlet")
public class AchatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            RequestDispatcher dispatcher;

            if(req.getParameter("id")!=null){
                String id = req.getParameter("id");

                PGConnect pgConnect = PGConnect.getInstance();
                Connection connection = pgConnect.getConnection();

                FicheAchat ficheAchat = new FicheAchat();
                ficheAchat.generateFiche(connection, id);

                req.setAttribute("fiche", ficheAchat);
                dispatcher = req.getRequestDispatcher("/views/?content=fiches/ficheAchat.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            
            List<Achat> achats = GenericRepo.findAll(Achat.class);

            req.setAttribute("achats", achats);

            dispatcher = req.getRequestDispatcher("/views/?content=listes/achat.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
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