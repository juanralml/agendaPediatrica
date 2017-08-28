/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "hijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hijo.findAll", query = "SELECT h FROM Hijo h"),
    @NamedQuery(name = "Hijo.findByCi", query = "SELECT h FROM Hijo h WHERE h.ci = :ci"),
    @NamedQuery(name = "Hijo.findByPadreId", query = "SELECT h FROM Hijo h WHERE h.padreId = :padreId"),
    @NamedQuery(name = "Hijo.findByNombres", query = "SELECT h FROM Hijo h WHERE h.nombres = :nombres"),
    @NamedQuery(name = "Hijo.findByApellidos", query = "SELECT h FROM Hijo h WHERE h.apellidos = :apellidos"),
    @NamedQuery(name = "Hijo.findByFechaNacimiento", query = "SELECT h FROM Hijo h WHERE h.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Hijo.findByLugarNacimiento", query = "SELECT h FROM Hijo h WHERE h.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "Hijo.findBySexo", query = "SELECT h FROM Hijo h WHERE h.sexo = :sexo"),
    @NamedQuery(name = "Hijo.findByNacionalidad", query = "SELECT h FROM Hijo h WHERE h.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Hijo.findByDireccion", query = "SELECT h FROM Hijo h WHERE h.direccion = :direccion"),
    @NamedQuery(name = "Hijo.findByDepartamento", query = "SELECT h FROM Hijo h WHERE h.departamento = :departamento"),
    @NamedQuery(name = "Hijo.findByMunicipio", query = "SELECT h FROM Hijo h WHERE h.municipio = :municipio"),
    @NamedQuery(name = "Hijo.findByBarrio", query = "SELECT h FROM Hijo h WHERE h.barrio = :barrio"),
    @NamedQuery(name = "Hijo.findByReferenciaUbicacion", query = "SELECT h FROM Hijo h WHERE h.referenciaUbicacion = :referenciaUbicacion"),
    @NamedQuery(name = "Hijo.findByTelefonoContacto", query = "SELECT h FROM Hijo h WHERE h.telefonoContacto = :telefonoContacto"),
    @NamedQuery(name = "Hijo.findBySeguroMedico", query = "SELECT h FROM Hijo h WHERE h.seguroMedico = :seguroMedico"),
    @NamedQuery(name = "Hijo.findByAlergiaContraindicacion", query = "SELECT h FROM Hijo h WHERE h.alergiaContraindicacion = :alergiaContraindicacion")})
public class Hijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ci")
    private String ci;
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 100)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 100)
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 100)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 100)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 100)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 100)
    @Column(name = "referencia_ubicacion")
    private String referenciaUbicacion;
    @Size(max = 100)
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @Size(max = 100)
    @Column(name = "seguro_medico")
    private String seguroMedico;
    @Size(max = 100)
    @Column(name = "alergia_contraindicacion")
    private String alergiaContraindicacion;
    @JoinColumn(name = "padre_id", referencedColumnName = "id")
    @ManyToOne
    private Usuarios padreId;

    public Hijo() {
    }

    public Hijo(String ci) {
        this.ci = ci;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getReferenciaUbicacion() {
        return referenciaUbicacion;
    }

    public void setReferenciaUbicacion(String referenciaUbicacion) {
        this.referenciaUbicacion = referenciaUbicacion;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getAlergiaContraindicacion() {
        return alergiaContraindicacion;
    }

    public void setAlergiaContraindicacion(String alergiaContraindicacion) {
        this.alergiaContraindicacion = alergiaContraindicacion;
    }

    public Usuarios getPadreId() {
        return padreId;
    }

    public void setPadreId(Usuarios padreId) {
        this.padreId = padreId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ci != null ? ci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hijo)) {
            return false;
        }
        Hijo other = (Hijo) object;
        if ((this.ci == null && other.ci != null) || (this.ci != null && !this.ci.equals(other.ci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Hijo[ ci=" + ci + " ]";
    }
    
}
