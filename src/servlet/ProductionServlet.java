package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.PGConnect;
import database.GenericRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MvtStockIngredient;
import model.MvtStockProduit;
import model.Production;
import model.ProductionFille;
import model.Produit;

@WebServlet(name = "ProductionServlet", urlPatterns = "/productionServlet")
public class ProductionServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");

        String date = req.getParameter("date");
        String description = req.getParameter("desc");

        ArrayList<String> prodValues = new ArrayList<String>();
        ArrayList<String> quantiteValues = new ArrayList<String>();

        PGConnect.getInstance().beginTransaction();

        String target = "/Boulangerie/prepaProduction";
        try {

            Map<String, ArrayList> map = new HashMap<String, ArrayList>();
            List<Produit> produits = GenericRepo.findAll(Produit.class);
            for(int i=1; i<produits.size(); i++){
                if(req.getParameter("quantite-"+i)==null){
                    break;
                }
                prodValues.add(req.getParameter("produit-"+i));
                quantiteValues.add(req.getParameter("quantite-"+i));
            }
            map.put("produits", prodValues);
            map.put("quantites", quantiteValues);
            
            MvtStockIngredient.ingredientProduction(date, map);
            
            MvtStockProduit.produitProduction(date, map);

            Production production = new Production();
            production.setDateProduction(date);
            production.setDescription(description);
            production = GenericRepo.save(production);

            ProductionFille.productionInsert(production.getId(), map);

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
