/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "hijo_vacuna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HijoVacuna.findAll", query = "SELECT h FROM HijoVacuna h"),
    @NamedQuery(name = "HijoVacuna.findByHijoCi", query = "SELECT h FROM HijoVacuna h WHERE h.hijoVacunaPK.hijoCi = :hijoCi"),
    @NamedQuery(name = "HijoVacuna.findByVacunaId", query = "SELECT h FROM HijoVacuna h WHERE h.hijoVacunaPK.vacunaId = :vacunaId"),
    @NamedQuery(name = "HijoVacuna.findByFecha", query = "SELECT h FROM HijoVacuna h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HijoVacuna.findByLote", query = "SELECT h FROM HijoVacuna h WHERE h.lote = :lote"),
    @NamedQuery(name = "HijoVacuna.findByResponsable", query = "SELECT h FROM HijoVacuna h WHERE h.responsable = :responsable")})
public class HijoVacuna implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HijoVacunaPK hijoVacunaPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 2147483647)
    @Column(name = "lote")
    private String lote;
    @Size(max = 2147483647)
    @Column(name = "responsable")
    private String responsable;
    @JoinColumn(name = "hijo_ci", referencedColumnName = "ci", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hijo hijo;
    @JoinColumn(name = "vacuna_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vacunas vacunas;

    public HijoVacuna() {
    }

    public HijoVacuna(HijoVacunaPK hijoVacunaPK) {
        this.hijoVacunaPK = hijoVacunaPK;
    }

    public HijoVacuna(String hijoCi, int vacunaId) {
        this.hijoVacunaPK = new HijoVacunaPK(hijoCi, vacunaId);
    }

    public HijoVacunaPK getHijoVacunaPK() {
        return hijoVacunaPK;
    }

    public void setHijoVacunaPK(HijoVacunaPK hijoVacunaPK) {
        this.hijoVacunaPK = hijoVacunaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    public Vacunas getVacunas() {
        return vacunas;
    }

    public void setVacunas(Vacunas vacunas) {
        this.vacunas = vacunas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hijoVacunaPK != null ? hijoVacunaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HijoVacuna)) {
            return false;
        }
        HijoVacuna other = (HijoVacuna) object;
        if ((this.hijoVacunaPK == null && other.hijoVacunaPK != null) || (this.hijoVacunaPK != null && !this.hijoVacunaPK.equals(other.hijoVacunaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HijoVacuna[ hijoVacunaPK=" + hijoVacunaPK + " ]";
    }
    
}
