package servlet;

import java.io.IOException;
// import java.util.List;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genre;
import model.Vendeur;

@WebServlet(name="RechercheCommission", urlPatterns="/rechercheCommission")
public class RechercheCommission extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Vendeur vendeur = new Vendeur();

            List<Vendeur> vendeurs = vendeur.commissionVendeur(200000, 5, "", "", "");

            List<Genre> genres = GenericRepo.findAll(Genre.class);
            
            req.setAttribute("vendeurs", vendeurs);
            req.setAttribute("genres", genres);
    
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=listes/commissionVendeur.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String debut = req.getParameter("debut");
        String fin = req.getParameter("fin");
        String genre = req.getParameter("genre");
        try{

            Vendeur vendeur = new Vendeur();
            List<Vendeur> vendeurs = vendeur.commissionVendeur(200000, 5, debut, fin, genre);

            List<Genre> genres = GenericRepo.findAll(Genre.class);
            
            req.setAttribute("vendeurs", vendeurs);

            req.setAttribute("genres", genres);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=listes/commissionVendeur.jsp");
            dispatcher.forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
