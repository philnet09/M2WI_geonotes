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
public class ParcoursClientPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "refparcours")
    private int refparcours;
    @Basic(optional = false)
    @Column(name = "refclient")
    private int refclient;

    public ParcoursClientPK() {
    }

    public ParcoursClientPK(int refparcours, int refclient) {
        this.refparcours = refparcours;
        this.refclient = refclient;
    }

    public int getRefparcours() {
        return refparcours;
    }

    public void setRefparcours(int refparcours) {
        this.refparcours = refparcours;
    }

    public int getRefclient() {
        return refclient;
    }

    public void setRefclient(int refclient) {
        this.refclient = refclient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) refparcours;
        hash += (int) refclient;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParcoursClientPK)) {
            return false;
        }
        ParcoursClientPK other = (ParcoursClientPK) object;
        if (this.refparcours != other.refparcours) {
            return false;
        }
        if (this.refclient != other.refclient) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.ParcoursClientPK[refparcours=" + refparcours + ", refclient=" + refclient + "]";
    }

}
