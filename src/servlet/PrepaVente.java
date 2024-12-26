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
import model.Produit;

@WebServlet(name="PrepaVente", urlPatterns="/prepaVente")
public class PrepaVente extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            
            if(req.getParameter("error")!=null){
                req.setAttribute("error", req.getParameter("error"));
            }

            List<Produit> produits = GenericRepo.findAll(Produit.class);

            req.setAttribute("produits", produits);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=vente.jsp");
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
