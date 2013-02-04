/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.google.gson.Gson;
import ejb.adminLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "UniqueNomNote", urlPatterns = {"/UniqueNomNote"})
public class UniqueNomNote extends HttpServlet {
    @EJB
    private adminLocal admin;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson json = new Gson();
        response.setContentType("application/json");
        String nomNote = request.getParameter("nomNote");
        if(admin.nomNoteUnique(nomNote))
            response.getWriter().write(json.toJson(1));//nom unique
        else
            response.getWriter().write(json.toJson(0));//nom n'est pas unique
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
