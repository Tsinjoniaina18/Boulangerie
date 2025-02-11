package servlet;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet(name="PrepaChangementPrix", urlPatterns="/prepaChangementPrix")
public class PrepaChangementPrix extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try{
            List<Produit> produits = GenericRepo.findAll(Produit.class);

            req.setAttribute("produits", produits);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/?content=ajout/changementPrix.jsp");
            requestDispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
    }

}
