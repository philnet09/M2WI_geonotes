/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import jpa.Utilisateur;

/**
 *
 * @author Administrateur
 */
@Local
public interface UtilisateursLocal {

    public boolean emailUnique(java.lang.String email);
    public void ajouterUtilisateur(Utilisateur u);
    public boolean utilisateurExiste(java.lang.String email, java.lang.String mdp);
    public jpa.Utilisateur getUtilisateur(java.lang.String email, java.lang.String mdp);
   
    
}
