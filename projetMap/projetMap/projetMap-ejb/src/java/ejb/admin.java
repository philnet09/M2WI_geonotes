/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.*;

/**
 *
 * @author Administrateur
 */
@Stateless
public class admin implements adminLocal {

    @PersistenceContext(unitName = "projetMap-ejbPU")
    private EntityManager em;

    public Collection<Note> getAllNotes() {

        Query query = em.createNamedQuery("Note.findAll");
        return query.getResultList();
    }

    public void ajouterNote(Note note) {
        em.persist(note);
    }

    @Override
    public boolean nomNoteUnique(String nomNote) {
        Query query = em.createNamedQuery("Note.uniquenomNote").setParameter("nomNote", nomNote);
        return Integer.parseInt(query.getSingleResult().toString()) == 0;
    }

    public void supprimerNote(int id) {
        Query query = em.createNamedQuery("Note.supprimer").setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Note modifierNote(int id, String nom, String desccription) {
        Query query = em.createNamedQuery("Note.modifierNote").setParameter("id", id)
                .setParameter("nom", nom)
                .setParameter("description", desccription);
        query.executeUpdate();
        query = em.createNamedQuery("Note.findById").setParameter("id", id);
        return (Note) query.getSingleResult();
    }
}
