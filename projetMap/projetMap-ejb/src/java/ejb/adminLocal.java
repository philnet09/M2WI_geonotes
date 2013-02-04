/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Collection;
import javax.ejb.Local;
import jpa.Note;
import jpa.Parcours;
import jpa.NoteParcours;

/**
 *
 * @author Administrateur
 */
@Local
public interface adminLocal {

    //Notes
    public Collection<Note> getAllNotes();
    public void ajouterNote(jpa.Note note);
    public boolean nomNoteUnique(java.lang.String nomNote);
    public void supprimerNote(int id);
    public Note modifierNote(int id,String nom, String desc);
    public int IdNotebyNom(String nom);
    public int IdNotebyLatLong(String nom);
    public int maxIdNote();
    public Object getNoteWithID(int id);
    
    //Parcours
    public Collection<Parcours> getAllParcours();
    public void ajouterParcours(jpa.Parcours parcours);
    public boolean nomParcoursUnique(java.lang.String nomParcours);
    public int maxIdParcours();
    public void supprimerParcours(int id);
    
    
    //NoteParcours
    public void ajouterNoteParcours(jpa.NoteParcours noteparcours);
    public Collection<NoteParcours> getNoteParcours(int idparcours);
    public void supprimerNoteParcours(int id);
}
