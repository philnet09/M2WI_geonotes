/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "parcours_client")
@NamedQueries({
    @NamedQuery(name = "ParcoursClient.findAll", query = "SELECT p FROM ParcoursClient p"),
    @NamedQuery(name = "ParcoursClient.findByRefparcours", query = "SELECT p FROM ParcoursClient p WHERE p.parcoursClientPK.refparcours = :refparcours"),
    @NamedQuery(name = "ParcoursClient.findByRefclient", query = "SELECT p FROM ParcoursClient p WHERE p.parcoursClientPK.refclient = :refclient"),
    @NamedQuery(name = "ParcoursClient.findByTpsrealise", query = "SELECT p FROM ParcoursClient p WHERE p.tpsrealise = :tpsrealise")})
public class ParcoursClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParcoursClientPK parcoursClientPK;
    @Basic(optional = false)
    @Column(name = "tpsrealise")
    private int tpsrealise;
    @JoinColumn(name = "refclient", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;
    @JoinColumn(name = "refparcours", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parcours parcours;

    public ParcoursClient() {
    }

    public ParcoursClient(ParcoursClientPK parcoursClientPK) {
        this.parcoursClientPK = parcoursClientPK;
    }

    public ParcoursClient(ParcoursClientPK parcoursClientPK, int tpsrealise) {
        this.parcoursClientPK = parcoursClientPK;
        this.tpsrealise = tpsrealise;
    }

    public ParcoursClient(int refparcours, int refclient) {
        this.parcoursClientPK = new ParcoursClientPK(refparcours, refclient);
    }

    public ParcoursClientPK getParcoursClientPK() {
        return parcoursClientPK;
    }

    public void setParcoursClientPK(ParcoursClientPK parcoursClientPK) {
        this.parcoursClientPK = parcoursClientPK;
    }

    public int getTpsrealise() {
        return tpsrealise;
    }

    public void setTpsrealise(int tpsrealise) {
        this.tpsrealise = tpsrealise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parcoursClientPK != null ? parcoursClientPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParcoursClient)) {
            return false;
        }
        ParcoursClient other = (ParcoursClient) object;
        if ((this.parcoursClientPK == null && other.parcoursClientPK != null) || (this.parcoursClientPK != null && !this.parcoursClientPK.equals(other.parcoursClientPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.ParcoursClient[parcoursClientPK=" + parcoursClientPK + "]";
    }

}
