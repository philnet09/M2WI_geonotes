/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.*;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "parcours")
@NamedQueries({
    @NamedQuery(name = "Parcours.findAll", query = "SELECT p FROM Parcours p"),
    @NamedQuery(name = "Parcours.findAllwithdetails", query = "SELECT p.id as idparcours, p.nom, p.distance, p.temps, np.ordre, n.latitude as lenom FROM Parcours p, NoteParcours np, Note n WHERE np.noteParcoursPK.refnote=n.id AND np.noteParcoursPK.refparcours = p.id GROUP BY p.id"),
    @NamedQuery(name = "Parcours.maxParcours", query = "SELECT p.id FROM Parcours p WHERE p.id = (SELECT MAX(p2.id) FROM Parcours p2)"),
    @NamedQuery(name = "Parcours.uniquenomParcours", query = "SELECT count(p.nom) FROM Parcours p WHERE p.nom = :nomParcours"),
    @NamedQuery(name = "Parcours.supprimer", query = "delete FROM Parcours p WHERE p.id = :id"),    
    @NamedQuery(name = "Parcours.findById", query = "SELECT p FROM Parcours p WHERE p.id = :id"),
    @NamedQuery(name = "Parcours.findByNom", query = "SELECT p FROM Parcours p WHERE p.nom = :nom"),
    @NamedQuery(name = "Parcours.findByDistance", query = "SELECT p FROM Parcours p WHERE p.distance = :distance"),
    @NamedQuery(name = "Parcours.findByTemps", query = "SELECT p FROM Parcours p WHERE p.temps = :temps")})
public class Parcours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "distance")
    private double distance;
    @Basic(optional = false)
    @Column(name = "temps")
    private int temps;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parcours")
    private Collection<NoteParcours> noteParcoursCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parcours")
    private Collection<ParcoursClient> parcoursClientCollection;

    public Parcours() {
    }

    public Parcours(Integer id) {
        this.id = id;
    }

    public Parcours(Integer id, String nom, double distance, int temps) {
        this.id = id;
        this.nom = nom;
        this.distance = distance;
        this.temps = temps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public Collection<NoteParcours> getNoteParcoursCollection() {
        return noteParcoursCollection;
    }

    public void setNoteParcoursCollection(Collection<NoteParcours> noteParcoursCollection) {
        this.noteParcoursCollection = noteParcoursCollection;
    }

    public Collection<ParcoursClient> getParcoursClientCollection() {
        return parcoursClientCollection;
    }

    public void setParcoursClientCollection(Collection<ParcoursClient> parcoursClientCollection) {
        this.parcoursClientCollection = parcoursClientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcours)) {
            return false;
        }
        Parcours other = (Parcours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Parcours[id=" + id + "]";
    }

}
