package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import connection.PGConnect;
import database.GenericRepo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Template;

@WebServlet(name="TestServlet", urlPatterns="/testServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Template aaa = new Template();
            List<Template> tests = GenericRepo.findAll(Template.class);
    
    
            req.setAttribute("coco", tests);
            RequestDispatcher dispatch = req.getRequestDispatcher("/views/test.jsp");
            dispatch.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");

        try {
            // Obtenir connection simple
            /*PGConnect pgConnect = PGConnect.getInstance();

            Connection co = pgConnect.getConnection();*/

            Template test = new Template();

            // test.setId("TEST00001");
            test.setTexte(nom);
            test.setNombre(prenom);

            GenericRepo.save(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}