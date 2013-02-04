package admin;

import com.google.gson.Gson;
import ejb.adminLocal;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.Parcours;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "getAllParcours", urlPatterns = {"/getAllParcours"})
public class getAllParcours extends HttpServlet {

    @EJB
    private adminLocal admin;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson json = new Gson();
        response.getWriter().write(json.toJson(admin.getAllParcours()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
