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
    
    public int IdNotebyNom(String nom){
        Query query = em.createNamedQuery("Note.findIdByNom").setParameter("nom", nom);
        if(!query.getResultList().isEmpty()) return Integer.parseInt(query.getSingleResult().toString());
        else return 0;
    }
    
    public int IdNotebyLatLong(String latlong){
        int idretour=0;
        if(latlong.contains(",")){
            String tablatlong[]=latlong.split(",");
            Query query = em.createNamedQuery("Note.findIdByLatLong").setParameter("lat", tablatlong[0]).setParameter("long", tablatlong[1]);
            if(!query.getResultList().isEmpty()){
                idretour = Integer.parseInt(query.getSingleResult().toString());
            }   
        }
        return idretour;
    }
    
    public int maxIdNote(){
        Query query = em.createNamedQuery("Note.maxNote");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public Object getNoteWithID(int id){
        Query query = em.createNamedQuery("Note.findById").setParameter("id", id);
        return query.getSingleResult();
    }
    
    public Collection<Parcours> getAllParcours() {
        Query query = em.createNamedQuery("Parcours.findAll");
        return  query.getResultList();
    }
        
    public void ajouterParcours(Parcours parcours){
        em.persist(parcours);
    }

    @Override
    public boolean nomParcoursUnique(String nomParcours) {
        Query query = em.createNamedQuery("Parcours.uniquenomParcours").setParameter("nomParcours", nomParcours);
        return Integer.parseInt(query.getSingleResult().toString()) == 0;
    }

    public int maxIdParcours(){
        Query query = em.createNamedQuery("Parcours.maxParcours");
        return Integer.parseInt(query.getSingleResult().toString());
    }
    
    
    public void ajouterNoteParcours(NoteParcours noteparcours){
        em.persist(noteparcours);
    }
    
    public Collection<NoteParcours> getNoteParcours(int idparcours){
        Query query = em.createNamedQuery("NoteParcours.findByRefparcours").setParameter("refparcours", idparcours);
        return  query.getResultList();
    }
    
    public void supprimerNoteParcours(int id) {
        Query query = em.createNamedQuery("NoteParcours.supprimerparcours").setParameter("id", id);
        query.executeUpdate();
    }
        
     public void supprimerParcours(int id) {
        Query query = em.createNamedQuery("Parcours.supprimer").setParameter("id", id);
        query.executeUpdate();
    }
}
