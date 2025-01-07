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
import model.Unite;

@WebServlet(name="PrepaIngredient", urlPatterns="/prepaIngredient")
public class PrepaIngredient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {

            List<Unite> unites = GenericRepo.findAll(Unite.class);

            req.setAttribute("unites", unites);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=ingredient.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
    }
}
