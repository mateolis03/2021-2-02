/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "CONDUCTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c")
    , @NamedQuery(name = "Conductor.findByConductorid", query = "SELECT c FROM Conductor c WHERE c.conductorid = :conductorid")
    , @NamedQuery(name = "Conductor.findByNombre", query = "SELECT c FROM Conductor c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Conductor.findByCedula", query = "SELECT c FROM Conductor c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "Conductor.findByEmail", query = "SELECT c FROM Conductor c WHERE c.email = :email")
    , @NamedQuery(name = "Conductor.findByTelefono", query = "SELECT c FROM Conductor c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Conductor.findByPassword", query = "SELECT c FROM Conductor c WHERE c.password = :password")})
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "CONDUCTORID")
    private String conductorid;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 40)
    @Column(name = "CEDULA")
    private String cedula;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 40)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 30)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 40)
    @Column(name = "PASSWORD")
    private String password;
    @JoinTable(name = "CONDUCTOR_ENVIO", joinColumns = {
        @JoinColumn(name = "CONDUCTOR_ID", referencedColumnName = "CONDUCTORID")}, inverseJoinColumns = {
        @JoinColumn(name = "ENVIO_ID", referencedColumnName = "ENVIOID")})
    @ManyToMany
    private Collection<Envio> envioCollection;

    public Conductor() {
    }

    public Conductor(String conductorid) {
        this.conductorid = conductorid;
    }

    public String getConductorid() {
        return conductorid;
    }

    public void setConductorid(String conductorid) {
        this.conductorid = conductorid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Envio> getEnvioCollection() {
        return envioCollection;
    }

    public void setEnvioCollection(Collection<Envio> envioCollection) {
        this.envioCollection = envioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conductorid != null ? conductorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.conductorid == null && other.conductorid != null) || (this.conductorid != null && !this.conductorid.equals(other.conductorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Conductor[ conductorid=" + conductorid + " ]";
    }
    
}
