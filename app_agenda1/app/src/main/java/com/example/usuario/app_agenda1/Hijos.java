package com.example.usuario.app_agenda1;

/**
 * Created by usuario on 06/11/2017.
 */

public class Hijos {
    private String nombre;
    private String edad;
    private String sexo;
    private String hijoId;

    public Hijos() {
    }

    public Hijos(String nombre, String edad, String sexo, String hijoId) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.hijoId = hijoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getHijoId() {
        return hijoId;
    }

    public void setHijoId(String hijoId) {
        this.hijoId = hijoId;
    }
}
