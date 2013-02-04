/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Collection;
import javax.ejb.Local;
import jpa.Note;

/**
 *
 * @author Administrateur
 */
@Local
public interface adminLocal {

    public Collection<Note> getAllNotes();

    public void ajouterNote(jpa.Note note);
     public boolean nomNoteUnique(java.lang.String nomNote);
    public void supprimerNote(int id);
    public Note modifierNote(int id,String nom, String desc);

}
