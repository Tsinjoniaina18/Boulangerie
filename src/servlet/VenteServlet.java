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
import model.MvtStockProduit;
import model.Produit;
import model.VenteFille;
import model.VenteProduit;

@WebServlet(name="VenteServlet", urlPatterns="/venteServlet")
public class VenteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String date = req.getParameter("date");
        String description = req.getParameter("desc");

        ArrayList<String> produits = new ArrayList<String>();
        ArrayList<String> quantites = new ArrayList<>();

        String target = "/Boulangerie/prepaVente";

        PGConnect.getInstance().beginTransaction();
        try {
            Map<String, ArrayList> map = new HashMap<String, ArrayList>();
            List<Produit> prods = GenericRepo.findAll(Produit.class);
            for(int i=1; i<=prods.size(); i++){
                if(req.getParameter("quantite-"+i)==null){
                    break;
                }
                produits.add(req.getParameter("produit-"+i));
                quantites.add(req.getParameter("quantite-"+i));
            }
            map.put("produits", produits);
            map.put("quantites", quantites);

            MvtStockProduit.venteProduit(date, map);

            VenteProduit vente = new VenteProduit();
            vente.setDateVenteProduit(date);
            vente.setDescription(description);
            vente = GenericRepo.save(vente);

            VenteFille.venteProduit(vente.getId(), map);

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
