package servlet;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.HistoriqueProduit;
import model.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet(name="ChangementPrix", urlPatterns="/changementPrix")
public class ChangementPrix extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try{
            HistoriqueProduit historiqueProduit = new HistoriqueProduit();
            List<HistoriqueProduit> historiqueProduits = historiqueProduit.findHistoriquePrix("");

            if(req.getParameter("produit") !=null){
                System.out.println("Miditra");
                historiqueProduits = historiqueProduit.findHistoriquePrix(req.getParameter("produit"));
            }

            List<Produit> produits = GenericRepo.findAll(Produit.class);

            req.setAttribute("historique", historiqueProduits);
            req.setAttribute("produits", produits);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/?content=listes/changementPrix.jsp");
            requestDispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String idProduit = req.getParameter("produit");
        String date = req.getParameter("date");
        String prix = req.getParameter("prix");

        try{
            HistoriqueProduit historiqueProduit = new HistoriqueProduit();
            historiqueProduit.setIdProduit(idProduit);
            historiqueProduit.setDateChangement(date);
            historiqueProduit.setPrix(prix);

            GenericRepo.save(historiqueProduit);

            Produit produit = GenericRepo.findById(idProduit, Produit.class);
            produit.setPrix(prix);

            GenericRepo.save(produit);

            resp.sendRedirect("/Boulangerie/views/index.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
