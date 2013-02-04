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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "note")
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.uniquenomNote", query = "SELECT count(n) FROM Note n WHERE n.nom = :nomNote"),
    @NamedQuery(name = "Note.supprimer", query = "delete FROM Note n WHERE n.id = :id"),
    @NamedQuery(name = "Note.modifierNote", query = "Update Note n SET n.nom = :nom , n.description = :description WHERE n.id = :id"),
    @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id"),
    @NamedQuery(name = "Note.findByNom", query = "SELECT n FROM Note n WHERE n.nom = :nom"),
    @NamedQuery(name = "Note.findByLatitude", query = "SELECT n FROM Note n WHERE n.latitude = :latitude"),
    @NamedQuery(name = "Note.findByLongitude", query = "SELECT n FROM Note n WHERE n.longitude = :longitude"),
    @NamedQuery(name = "Note.findByDate", query = "SELECT n FROM Note n WHERE n.date = :date"),
    @NamedQuery(name = "Note.findByUrlPhoto", query = "SELECT n FROM Note n WHERE n.urlPhoto = :urlPhoto"),
    @NamedQuery(name = "Note.findByVisible", query = "SELECT n FROM Note n WHERE n.visible = :visible")})
public class Note implements Serializable {
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
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private String longitude;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date")
    private String date;
    @Column(name = "url_photo")
    private String urlPhoto;
    @Basic(optional = false)
    @Column(name = "visible")
    private boolean visible;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note")
    private Collection<NoteParcours> noteParcoursCollection;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(String nom, String latitude, String longitude, String description, String date, boolean visible) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.date = date;
        this.visible = visible;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Collection<NoteParcours> getNoteParcoursCollection() {
        return noteParcoursCollection;
    }

    public void setNoteParcoursCollection(Collection<NoteParcours> noteParcoursCollection) {
        this.noteParcoursCollection = noteParcoursCollection;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Note[id=" + id + "]";
    }

}
