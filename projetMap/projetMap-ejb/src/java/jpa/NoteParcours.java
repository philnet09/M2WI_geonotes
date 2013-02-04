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
@Table(name = "note_parcours")
@NamedQueries({
    @NamedQuery(name = "NoteParcours.findAll", query = "SELECT n FROM NoteParcours n"),
    @NamedQuery(name = "NoteParcours.findByRefnote", query = "SELECT n FROM NoteParcours n WHERE n.noteParcoursPK.refnote = :refnote"),
    @NamedQuery(name = "NoteParcours.findByRefparcours", query = "SELECT n FROM NoteParcours n WHERE n.noteParcoursPK.refparcours = :refparcours"),
    @NamedQuery(name = "NoteParcours.supprimerparcours", query = "delete FROM NoteParcours n WHERE n.noteParcoursPK.refparcours = :id"),    
    @NamedQuery(name = "NoteParcours.findByOrdre", query = "SELECT n FROM NoteParcours n WHERE n.ordre = :ordre")})
public class NoteParcours implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NoteParcoursPK noteParcoursPK;
    @Basic(optional = false)
    @Column(name = "ordre")
    private int ordre;
    @JoinColumn(name = "refparcours", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parcours parcours;
    @JoinColumn(name = "refnote", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Note note;

    public NoteParcours() {
    }

    public NoteParcours(NoteParcoursPK noteParcoursPK) {
        this.noteParcoursPK = noteParcoursPK;
    }

    public NoteParcours(NoteParcoursPK noteParcoursPK, int ordre) {
        this.noteParcoursPK = noteParcoursPK;
        this.ordre = ordre;
    }

    public NoteParcours(int refnote, int refparcours) {
        this.noteParcoursPK = new NoteParcoursPK(refnote, refparcours);
    }
        
    public NoteParcoursPK getNoteParcoursPK() {
        return noteParcoursPK;
    }

    public void setNoteParcoursPK(NoteParcoursPK noteParcoursPK) {
        this.noteParcoursPK = noteParcoursPK;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteParcoursPK != null ? noteParcoursPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoteParcours)) {
            return false;
        }
        NoteParcours other = (NoteParcours) object;
        if ((this.noteParcoursPK == null && other.noteParcoursPK != null) || (this.noteParcoursPK != null && !this.noteParcoursPK.equals(other.noteParcoursPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.NoteParcours[noteParcoursPK=" + noteParcoursPK + "]";
    }

}
