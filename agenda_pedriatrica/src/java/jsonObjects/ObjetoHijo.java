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
public class ObjetoHijo {
    String hijoCi;
    String nombre;
    String edad;
    String fechaNacimiento;
    String sexo;

    public ObjetoHijo() {
    }

    public ObjetoHijo(String hijoCi, String nombre, String edad, String fechaNacimiento, String sexo) {
        this.hijoCi = hijoCi;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }
    
    
    
    public String getHijoCi() {
        return hijoCi;
    }

    public void setHijoCi(String hijoCi) {
        this.hijoCi = hijoCi;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
    
}
