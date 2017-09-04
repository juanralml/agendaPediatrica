/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author usuario
 */
@Embeddable
public class HijoVacunaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "hijo_ci")
    private String hijoCi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vacuna_id")
    private int vacunaId;

    public HijoVacunaPK() {
    }

    public HijoVacunaPK(String hijoCi, int vacunaId) {
        this.hijoCi = hijoCi;
        this.vacunaId = vacunaId;
    }

    public String getHijoCi() {
        return hijoCi;
    }

    public void setHijoCi(String hijoCi) {
        this.hijoCi = hijoCi;
    }

    public int getVacunaId() {
        return vacunaId;
    }

    public void setVacunaId(int vacunaId) {
        this.vacunaId = vacunaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hijoCi != null ? hijoCi.hashCode() : 0);
        hash += (int) vacunaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HijoVacunaPK)) {
            return false;
        }
        HijoVacunaPK other = (HijoVacunaPK) object;
        if ((this.hijoCi == null && other.hijoCi != null) || (this.hijoCi != null && !this.hijoCi.equals(other.hijoCi))) {
            return false;
        }
        if (this.vacunaId != other.vacunaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HijoVacunaPK[ hijoCi=" + hijoCi + ", vacunaId=" + vacunaId + " ]";
    }
    
}
