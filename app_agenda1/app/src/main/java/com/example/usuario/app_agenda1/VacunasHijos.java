package com.example.usuario.app_agenda1;

/**
 * Created by usuario on 17/11/2017.
 */

public class VacunasHijos {
    private Integer idVacuna;
    private String ciHijo;
    private String nombreVacuna;
    private String aplicado;
    private String esquemaIdeal;

    public VacunasHijos(Integer idVacuna, String ciHijo, String nombreVacuna, String aplicado, String esquemaIdeal) {

        this.idVacuna = idVacuna;
        this.ciHijo = ciHijo;
        this.nombreVacuna = nombreVacuna;
        this.aplicado = aplicado;
        this.esquemaIdeal = esquemaIdeal;
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

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public String getAplicado() {
        return aplicado;
    }

    public void setAplicado(String aplicado) {
        this.aplicado = aplicado;
    }

    public String getEsquemaIdeal() {
        return esquemaIdeal;
    }

    public void setEsquemaIdeal(String esquemaIdeal) {
        this.esquemaIdeal = esquemaIdeal;
    }


}
