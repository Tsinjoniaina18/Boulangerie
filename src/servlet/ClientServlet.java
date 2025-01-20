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
import model.Client;
import model.VenteProduit;

@WebServlet(name="ClientServlet", urlPatterns="/clientServlet")
public class ClientServlet extends HttpServlet{
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");

        RequestDispatcher dispatch = req.getRequestDispatcher("/views/?content=listes/client.jsp");   
        dispatch.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String date = req.getParameter("date");
        String nom = req.getParameter("nom");
        try {
            if(date!=null){
                VenteProduit venteProduit = new VenteProduit();
                List<String> clients = venteProduit.listClient(date);

                req.setAttribute("clients", clients);
                req.setAttribute("date", date);

                RequestDispatcher dispatch = req.getRequestDispatcher("/views/?content=listes/client.jsp");
                
                dispatch.forward(req, resp);
            }else if(nom!=null){
                Client client = new Client();
                client.setNom(nom);
                GenericRepo.save(client);

            resp.sendRedirect("/Boulangerie/views/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
