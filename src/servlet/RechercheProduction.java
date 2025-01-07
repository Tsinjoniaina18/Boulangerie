package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            for(int i=0;i<conditions.size();i++){
                System.out.println(conditions.get(i));
            }
          
            List<Production> listProduction =Production.listProduction(conditions);
            for(int i=0;i<listProduction.size();i++){
                System.out.println(listProduction.get(i));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
}
}

