package servlet;

import java.io.IOException;

import database.GenericRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingredient;

@WebServlet(name="IngredientServlet", urlPatterns="/ingredientServlet")
public class IngredientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String nom = req.getParameter("nom");
        String description = req.getParameter("desc");
        String idUnite = req.getParameter("idUnite");
        try {
            
            Ingredient ingredient = new Ingredient();
            ingredient.setNom(nom);
            ingredient.setDescription(description);
            ingredient.setIdUnite(idUnite);

            GenericRepo.save(ingredient);

            resp.sendRedirect("/Boulangerie/prepaIngredient");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
