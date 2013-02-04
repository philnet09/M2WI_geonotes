/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.Utilisateur;

/**
 *
 * @author Administrateur
 */
@Stateless
public class Utilisateurs implements UtilisateursLocal {

    @PersistenceContext(unitName = "projetMap-ejbPU")
    private EntityManager em;

    @Override
    public boolean emailUnique(String email) {
        Query query = em.createNamedQuery("Utilisateur.uniqueEmail").setParameter("email", email);
        return Integer.parseInt(query.getSingleResult().toString()) == 0;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
        em.persist(u);
    }

    @Override
    public boolean utilisateurExiste(String email, String mdp) {
        Query query = em.createNamedQuery("Utilisateur.findByMailAndByMdp").setParameter("email", email).setParameter("mdp", mdp);
        return Integer.parseInt(query.getSingleResult().toString()) == 1;
    }

    @Override
    public jpa.Utilisateur getUtilisateur(String email, String mdp) {
        Query query = em.createNamedQuery("Utilisateur.getByMailAndByMdp").setParameter("email", email).setParameter("mdp", mdp);
        return (Utilisateur) query.getSingleResult();
    }
    
}
