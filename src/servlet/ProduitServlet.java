package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.PGConnect;
import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Ingredient;
import model.Produit;
import model.Recette;
import model.fiche.FicheProduit;

@WebServlet(name="ProduitServlet", urlPatterns="/produitServlet")
public class ProduitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            RequestDispatcher dispatcher;

            if(req.getParameter("id")!=null){
                String id = req.getParameter("id");

                FicheProduit ficheProduit = new FicheProduit();
                ficheProduit.generateFiche(id);

                req.setAttribute("fiche", ficheProduit);
                dispatcher = req.getRequestDispatcher("/views/?content=fiches/ficheProduit.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            List<Categorie> categories = GenericRepo.findAll(Categorie.class);

            List<Produit> produits = Produit.stockProduit(null, null);

            req.setAttribute("categories", categories);
            req.setAttribute("produits", produits);

            dispatcher = req.getRequestDispatcher("/views/?content=stock/stockProduit.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");

        String nom = req.getParameter("nom");
        String description = req.getParameter("desc");
        String categorie = req.getParameter("categorie");
        String prix = req.getParameter("prix");
        String naturel = req.getParameter("naturel");

        ArrayList<String> ingValues = new ArrayList<String>();
        ArrayList<String> quantiteValues = new ArrayList<String>();

        PGConnect.getInstance().beginTransaction();

        String target = "/Boulangerie/prepaProduit";
        try {
            
            Map<String, ArrayList> map = new HashMap<String, ArrayList>();
            List<Ingredient> ingredients = GenericRepo.findAll(Ingredient.class);
            for(int i=1; i<ingredients.size(); i++){
                if(req.getParameter("quantite-"+i)==null){
                    if(i == 1){
                        throw new Exception("Un produit doit contenir au moin d'un ingredient");
                    }
                    break;
                }
                ingValues.add(req.getParameter("ingredient-"+i));
                quantiteValues.add(req.getParameter("quantite-"+i));
            }
            map.put("ingredients", ingValues);
            map.put("quantites", quantiteValues);
            
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setIdCategorie(categorie);
            produit.setPrix(prix);
            produit.setEstNature(naturel);
            
            produit = GenericRepo.save(produit);
            
            Recette.generateProduit(produit.getId(), map);

            PGConnect.getInstance().commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            PGConnect.getInstance().rollbackTransaction();
            target += "?error="+e.getMessage();
        }
        PGConnect.getInstance().endTransaction();

        resp.sendRedirect(target);
    }
}
