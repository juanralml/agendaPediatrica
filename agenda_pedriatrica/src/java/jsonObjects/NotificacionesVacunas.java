/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonObjects;

/**
 *
 * @author usuario
 */
public class NotificacionesVacunas {
    
    private Integer idVacuna;
    private String ciHijo;
    private String nameVacuna;
    private Integer ideal;
    private Integer diferencia;

    public NotificacionesVacunas() {
    }

    public NotificacionesVacunas(Integer idVacuna, String ciHijo, String nameVacuna, Integer ideal, Integer diferencia) {
        this.idVacuna = idVacuna;
        this.ciHijo = ciHijo;
        this.nameVacuna = nameVacuna;
        this.ideal = ideal;
        this.diferencia = diferencia;
    }

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getCiHijo() {
        return ciHijo;
    }

    public void setCiHijo(String ciHijo) {
        this.ciHijo = ciHijo;
    }

    public String getNameVacuna() {
        return nameVacuna;
    }

    public void setNameVacuna(String nameVacuna) {
        this.nameVacuna = nameVacuna;
    }

    public Integer getIdeal() {
        return ideal;
    }

    public void setIdeal(Integer ideal) {
        this.ideal = ideal;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    @Override
    public String toString() {
        return "NotificacionesVacunas{" + "idVacuna=" + idVacuna + ", ciHijo=" + ciHijo + ", nameVacuna=" + nameVacuna + ", ideal=" + ideal + ", diferencia=" + diferencia + '}';
    }
    
    
    
}
