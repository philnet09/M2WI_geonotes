/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.google.gson.Gson;
import ejb.UtilisateursLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import jax.UserWSService; 
import jpa.Utilisateur;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "connexion", urlPatterns = {"/connexion"})
public class connexion extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UserWS/UserWSService.wsdl")
    private UserWSService service;

    @EJB
    private UtilisateursLocal user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson json = new Gson();
        response.setContentType("application/json");
        EmailValidator ev = new EmailValidator();
        if (ev.validate(request.getParameter("email").trim())) {
            if (user.emailUnique(request.getParameter("email").trim())) {
                response.getWriter().write(json.toJson(1));//email valide et unique
            } else {
                response.getWriter().write(json.toJson(2));//email valide mais non unique
            }
        } else {
            response.getWriter().write(json.toJson(3));//email non valide
        }
    }

    @Override
    /*
     * cnx=1 il s'agit d'une connexion
     * cnx=0 il s'agit d'un nouveau profil
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("cnx").equals("1")) {
            String email = request.getParameter("email").trim();
            String mdp = request.getParameter("mdp").trim();
            if (email.equals("admin@admin.com") && mdp.equals("admin")) {
                request.getRequestDispatcher("WEB-INF/administration.jsp").forward(request, response);
            } else {
                if (user.utilisateurExiste(email, mdp)) {
                    request.setAttribute("user", user.getUtilisateur(email, mdp));
                    request.getRequestDispatcher("WEB-INF/Utilisation.jsp").forward(request, response);
                } else {
                    String erreur = "Connexion échoué";
                    request.setAttribute("erreurCnx", erreur);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
        }
        if (request.getParameter("cnx").equals("0")) {
            String nom = request.getParameter("nom").trim();
            String prenom = request.getParameter("prenom").trim();
            String email = request.getParameter("email1").trim();
            String mdp = request.getParameter("mdp1").trim();
            EmailValidator ev = new EmailValidator();
            if (ev.validate(email) && nom.length() > 1 && prenom.length() > 1 && user.emailUnique(email) && mdp.length() > 3) {
               Utilisateur u = new Utilisateur(email, mdp, nom, prenom);
               user.ajouterUtilisateur(u);
                request.getRequestDispatcher("WEB-INF/administration.jsp").forward(request, response);
            } else {
                String erreur = "Inscription échoué";
                request.setAttribute("erreurInsc", erreur);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

}
