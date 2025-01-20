package servlet;

import java.io.IOException;

import database.GenericRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Client;

@WebServlet(name="CategorieServlet", urlPatterns="/categorieServlet")
public class CategorieServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String nom = req.getParameter("nom");
        
        try{
            Categorie categorie = new Categorie();
            categorie.setNom(nom);



            GenericRepo.save(categorie);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/Boulangerie/views/index.jsp");
    }
    

    
}
