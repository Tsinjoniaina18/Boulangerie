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
import model.Conseil;

@WebServlet(name="ConseilServlet", urlPatterns="/conseilServlet")
public class ConseilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        try {
            List<Conseil> conseils = Conseil.rechercherConseils(null,null);

            req.setAttribute("conseils", conseils);
            RequestDispatcher dispatch = req.getRequestDispatcher("/views/?content=listes/conseil.jsp");
           
            dispatch.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String idProduit = req.getParameter("produit");
        String mois = req.getParameter("mois");
        String annee = req.getParameter("annee");

        try {
            Conseil conseil = new Conseil();
            conseil.setIdProduit(idProduit);
            conseil.setMois(mois);
            conseil.setAnnee(annee);

            GenericRepo.save(conseil);

            resp.sendRedirect("/Boulangerie/prepaConseil");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
