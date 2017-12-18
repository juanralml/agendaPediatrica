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
public class ObjetoVacunaHijoApp {
    private Integer idVacuna;
    private String ciHijo;
    private String nombreVacuna;
    private String aplicado;
    private String esquemaIdeal;
    private String fechaAplicacion;

    public ObjetoVacunaHijoApp() {
    }

    
    
    
    public ObjetoVacunaHijoApp(Integer idVacuna, String ciHijo, String nombreVacuna, String aplicado, String esquemaIdeal, String fechaAplicacion) {
        this.idVacuna = idVacuna;
        this.ciHijo = ciHijo;
        this.nombreVacuna = nombreVacuna;
        this.aplicado = aplicado;
        this.esquemaIdeal = esquemaIdeal;
        this.fechaAplicacion = fechaAplicacion;
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

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }
    
    
    
    
}
