package servlet;

import java.io.IOException;
import java.util.List;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingredient;

@WebServlet(name="PrepaAchat", urlPatterns="/prepaAchat")
public class PrepaAchat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            /*Ingredient ingredient = new Ingredient();
            ingredient.setNom("Ble");
            ingredient.setDescription("Petit");
            ingredient.setPrix(3000);
            GenericRepo.save(ingredient);*/

            List<Ingredient> ingredients = GenericRepo.findAll(Ingredient.class);

            req.setAttribute("ingredients", ingredients);
            RequestDispatcher dispatch = req.getRequestDispatcher("/views/?content=achat.jsp");
            dispatch.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
    }

}
