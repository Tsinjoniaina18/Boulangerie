package servlet;

import java.io.IOException;

import database.GenericRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Unite;

@WebServlet(name="UniteServlet", urlPatterns="/uniteServlet")
public class UniteServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String nom = req.getParameter("nom");
        String initiale = req.getParameter("initiale");
        try {

            Unite unite = new Unite();
            unite.setNom(nom);
            unite.setInitiale(initiale);

            GenericRepo.save(unite);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/Boulangerie/views/");
    }

}
