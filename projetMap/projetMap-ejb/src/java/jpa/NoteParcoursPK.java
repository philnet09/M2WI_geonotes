/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrateur
 */
@Embeddable
public class NoteParcoursPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "refnote")
    private int refnote;
    @Basic(optional = false)
    @Column(name = "refparcours")
    private int refparcours;

    public NoteParcoursPK() {
    }

    public NoteParcoursPK(int refnote, int refparcours) {
        this.refnote = refnote;
        this.refparcours = refparcours;
    }

    public int getRefnote() {
        return refnote;
    }

    public void setRefnote(int refnote) {
        this.refnote = refnote;
    }

    public int getRefparcours() {
        return refparcours;
    }

    public void setRefparcours(int refparcours) {
        this.refparcours = refparcours;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) refnote;
        hash += (int) refparcours;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoteParcoursPK)) {
            return false;
        }
        NoteParcoursPK other = (NoteParcoursPK) object;
        if (this.refnote != other.refnote) {
            return false;
        }
        if (this.refparcours != other.refparcours) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.NoteParcoursPK[refnote=" + refnote + ", refparcours=" + refparcours + "]";
    }

}
