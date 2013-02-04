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
import jax.UserServiceService;
import jax.UtilisateurPOJO;
import jpa.Utilisateur;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "connexion", urlPatterns = {"/connexion"})
public class connexion extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UtilisateurWS/UserServiceService.wsdl")
    private UserServiceService service;


    @EJB
    private UtilisateursLocal user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson json = new Gson();
        response.setContentType("application/json");
        EmailValidator ev = new EmailValidator();
        if (ev.validate(request.getParameter("email").trim())) {
            if (!this.emailUnique(request.getParameter("email").trim())) {
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
                if (this.utilisateurExiste(email, mdp)) {
                    request.setAttribute("user", this.getUtilisateur(email, mdp));
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
            if (ev.validate(email) && nom.length() > 1 && prenom.length() > 1 && !this.emailUnique(email) && mdp.length() > 3) {
                this.ajouterUtilisateur(email,mdp,nom,prenom);
//                Utilisateur util = new Utilisateur();
//                jax.UserWS port = service.getUserWSPort();
//                port.ajouterUtilisateur(nom, prenom, mdp, email);
                request.getRequestDispatcher("WEB-INF/administration.jsp").forward(request, response);
            } else {
                String erreur = "Inscription échoué";
                request.setAttribute("erreurInsc", erreur);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    private void ajouterUtilisateur(java.lang.String email, java.lang.String mdp, java.lang.String nom, java.lang.String prenom) {
        jax.UserService port = service.getUserServicePort();
        port.ajouterUtilisateur(email, mdp, nom, prenom);
    }

    private boolean emailUnique(java.lang.String email) {
        jax.UserService port = service.getUserServicePort();
        return port.emailUnique(email);
    }

    private UtilisateurPOJO getUtilisateur(java.lang.String email, java.lang.String mdp) {
        jax.UserService port = service.getUserServicePort();
        return port.getUtilisateur(email, mdp);
    }

    private boolean utilisateurExiste(java.lang.String email, java.lang.String mdp) {
        jax.UserService port = service.getUserServicePort();
        return port.utilisateurExiste(email, mdp);
    }



}
