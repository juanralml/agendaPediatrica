/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "vacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacunas.findAll", query = "SELECT v FROM Vacunas v"),
    @NamedQuery(name = "Vacunas.findById", query = "SELECT v FROM Vacunas v WHERE v.id = :id"),
    @NamedQuery(name = "Vacunas.findByDescripcion", query = "SELECT v FROM Vacunas v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Vacunas.findByEsquemaIdealMeses", query = "SELECT v FROM Vacunas v WHERE v.esquemaIdealMeses = :esquemaIdealMeses")})
public class Vacunas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "esquema_ideal_meses")
    private Integer esquemaIdealMeses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacunas")
    private Collection<HijoVacuna> hijoVacunaCollection;

    public Vacunas() {
    }

    public Vacunas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEsquemaIdealMeses() {
        return esquemaIdealMeses;
    }

    public void setEsquemaIdealMeses(Integer esquemaIdealMeses) {
        this.esquemaIdealMeses = esquemaIdealMeses;
    }

    @XmlTransient
    public Collection<HijoVacuna> getHijoVacunaCollection() {
        return hijoVacunaCollection;
    }

    public void setHijoVacunaCollection(Collection<HijoVacuna> hijoVacunaCollection) {
        this.hijoVacunaCollection = hijoVacunaCollection;
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
        if (!(object instanceof Vacunas)) {
            return false;
        }
        Vacunas other = (Vacunas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vacunas[ id=" + id + " ]";
    }
    
}
