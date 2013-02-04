/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

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
 * @author Philippe
 */
@WebServlet(name = "SupprimerParcours", urlPatterns = {"/SupprimerParcours"})
public class SupprimerParcours extends HttpServlet {
 @EJB
    private adminLocal admin;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        admin.supprimerNoteParcours(id);
        admin.supprimerParcours(id);
    }
}
