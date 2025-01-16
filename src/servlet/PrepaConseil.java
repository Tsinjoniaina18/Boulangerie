package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mois;
import model.Produit;

@WebServlet(name="prepaConseil", urlPatterns="/prepaConseil")
public class PrepaConseil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            List<Produit> produits = GenericRepo.findAll(Produit.class);

            req.setAttribute("produits", produits);

            ArrayList<Mois> mois = new ArrayList<Mois>();
            mois.add(new Mois(1, "Janvier"));
            mois.add(new Mois(2, "Fevrier"));
            mois.add(new Mois(3, "Mars"));
            mois.add(new Mois(4, "Avril"));
            mois.add(new Mois(5, "Mai"));
            mois.add(new Mois(6, "Juin"));
            mois.add(new Mois(7, "Juillet"));
            mois.add(new Mois(8, "Aout"));
            mois.add(new Mois(9, "Septembre"));
            mois.add(new Mois(10, "Octobre"));
            mois.add(new Mois(11, "Novembre"));
            mois.add(new Mois(12, "Decembre"));

            req.setAttribute("mois", mois);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=conseil.jsp");
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
