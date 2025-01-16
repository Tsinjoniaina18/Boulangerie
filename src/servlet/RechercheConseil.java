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
import model.Conseil;
import model.Ingredient;

@WebServlet(name="RechercheConseil", urlPatterns="/rechercheConseil")
public class RechercheConseil extends HttpServlet{
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
            
            List<Conseil> conseils = Conseil.rechercherConseils(req.getParameter("mois"), req.getParameter("annee"));

            req.setAttribute("conseils", conseils);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/?content=listes/conseil.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
