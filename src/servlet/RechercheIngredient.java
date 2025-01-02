package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ingredient;

@WebServlet(name="RechercheIngredient", urlPatterns="/rechercheIngredients")
public class RechercheIngredient extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        try {
            
            java.util.Enumeration<String> parameterNames = req.getParameterNames();

            ArrayList<String> conditions = new ArrayList<>();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);
    
                conditions.add(paramValue);
            }
            
            List<Ingredient> ingredients = Ingredient.stockIngredient(null, conditions);

            req.setAttribute("ingredients", ingredients);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=stockIngredient.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
