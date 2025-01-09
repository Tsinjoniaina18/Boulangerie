package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
// import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.Ingredient;
import model.Production;


@WebServlet(name="RechercheProduction", urlPatterns="/rechercheProduction")
public class RechercheProduction extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        try{
            java.util.Enumeration<String> parameterNames = req.getParameterNames();
            ArrayList<String> conditions = new ArrayList<>();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);
    
                conditions.add(paramValue);
            }
          
            List<Production> productions =Production.listProduction(conditions);
            
            List<Ingredient> ingredients = GenericRepo.findAll(Ingredient.class);
            List<Categorie> categories = GenericRepo.findAll(Categorie.class);
            req.setAttribute("ingredients", ingredients);
            req.setAttribute("categories", categories);
            
            req.setAttribute("productions", productions);

            RequestDispatcher dispatch = req.getRequestDispatcher("/views/?content=listes/production.jsp");
           
            dispatch.forward(req, resp);
        }
        catch(Exception e){
            e.printStackTrace();
        }
}
}

