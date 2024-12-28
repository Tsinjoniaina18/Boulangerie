package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="TestServlet", urlPatterns="/testServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            /*Categorie categorie = new Categorie();
            categorie.setNom("Pains");
            GenericRepo.save(categorie);*/

            /*Recette recette = new Recette();
            recette.setIdProduit("PROD00002");
            recette.setIdIngredient("ING00002");
            recette.setQuantite(2);

            GenericRepo.save(recette);*/

            /*Recette recette2 = new Recette();
            recette2.setIdProduit("PROD00001");
            recette2.setIdIngredient("ING00002");
            recette2.setQuantite(3);

            GenericRepo.save(recette2);*/
            out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}