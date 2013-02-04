/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import jpa.Note;
import jpa.NoteParcours;
import jpa.NoteParcoursPK;
import jpa.Parcours;

/**
 *
 * @author Philippe
 */
@WebServlet(name = "AjoutParcours", urlPatterns = {"/AjoutParcours"})
public class AjoutParcours extends HttpServlet {

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
        //ne pas enregistrer un parcours avec un nom existant deja
        if (admin.nomParcoursUnique(request.getParameter("nom"))) {
            //Insertion Parcours
            Parcours parcours = new Parcours(null,request.getParameter("nom"),Double.parseDouble(request.getParameter("distance")),Integer.parseInt(request.getParameter("temps")));
            admin.ajouterParcours(parcours);
            
            //Variables
            int indexarrivee = 2;
            Date d = new Date();
            Locale locale = Locale.FRENCH;
            DateFormat dateF = new SimpleDateFormat("Edd MMM yyyy", locale);
            int idnote;
            
            //Départ
            if(admin.IdNotebyLatLong(request.getParameter("depart"))==0){//Le départ n'existe pas, on le crée et ensuite on l'utilise
                Note notedepart = new Note(request.getParameter("depart"), request.getParameter("latdep"), request.getParameter("lngdep"), request.getParameter("depart"), dateF.format(d), true);
                admin.ajouterNote(notedepart);
                idnote = admin.maxIdNote();
            }
            else {
                idnote = admin.IdNotebyLatLong(request.getParameter("depart"));
            }
            //Insertion dans NoteParcours)
            NoteParcoursPK pkdepart = new NoteParcoursPK(idnote,admin.maxIdParcours());
            NoteParcours notepdepart = new NoteParcours(pkdepart,1);
            admin.ajouterNoteParcours(notepdepart);
            
            //Etape 1
            if(!request.getParameter("etape1").equals("null")){ //On a 1 étape
                indexarrivee=3;
                if(admin.IdNotebyLatLong(request.getParameter("etape1"))==0){//Le départ n'existe pas, on le crée et ensuite on l'utilise
                    Note noteetape1 = new Note(request.getParameter("etape1"), request.getParameter("latetp1"), request.getParameter("lngetp1"), request.getParameter("etape1"), dateF.format(d), true);
                    admin.ajouterNote(noteetape1);
                idnote = admin.maxIdNote();
                }
                else {
                    idnote = admin.IdNotebyLatLong(request.getParameter("etape1"));
                }
                //Insertion dans NoteParcours
                NoteParcoursPK pketape1 = new NoteParcoursPK(idnote,admin.maxIdParcours());
                NoteParcours notetape1 = new NoteParcours(pketape1,2);
                admin.ajouterNoteParcours(notetape1);
            }
            
            //Etape 2
            if(!request.getParameter("etape2").equals("null")){
                indexarrivee=4;
                if(admin.IdNotebyLatLong(request.getParameter("etape2"))==0){//Le départ n'existe pas, on le crée et ensuite on l'utilise
                    Note noteetape2 = new Note(request.getParameter("etape2"), request.getParameter("latetp2"), request.getParameter("lngetp2"), request.getParameter("etape2"), dateF.format(d), true);
                    admin.ajouterNote(noteetape2);
                idnote = admin.maxIdNote();
                }
                else {
                    idnote = admin.IdNotebyLatLong(request.getParameter("etape2"));
                }
                //Insertion dans NoteParcours
                NoteParcoursPK pketape2 = new NoteParcoursPK(idnote,admin.maxIdParcours());
                NoteParcours notetape2 = new NoteParcours(pketape2,3);
                admin.ajouterNoteParcours(notetape2);
            }
            
        
            //Arrivée
            if(admin.IdNotebyLatLong(request.getParameter("arrivee"))==0){//Le départ n'existe pas, on le crée et ensuite on l'utilise
                Note notearrivee = new Note(request.getParameter("arrivee"), request.getParameter("latarr"), request.getParameter("lngarr"), request.getParameter("arrivee"), dateF.format(d), true);
                admin.ajouterNote(notearrivee);
                idnote = admin.maxIdNote();       
            }
            else {
                idnote = admin.IdNotebyLatLong(request.getParameter("arrivee"));
            }
            //Insertion dans NoteParcours
            NoteParcoursPK pkarrivee = new NoteParcoursPK(idnote,admin.maxIdParcours());
            NoteParcours noteparrivee = new NoteParcours(pkarrivee,indexarrivee);
            admin.ajouterNoteParcours(noteparrivee);
            
            
        }
    }
}
