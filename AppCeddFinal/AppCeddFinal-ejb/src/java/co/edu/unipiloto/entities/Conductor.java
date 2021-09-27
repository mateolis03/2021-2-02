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
 * @author edwin
 */
@Entity
@Table(name = "CONDUCTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c")
    , @NamedQuery(name = "Conductor.findById", query = "SELECT c FROM Conductor c WHERE c.id = :id")
    , @NamedQuery(name = "Conductor.findByNombre", query = "SELECT c FROM Conductor c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Conductor.findByCedula", query = "SELECT c FROM Conductor c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "Conductor.findByEmail", query = "SELECT c FROM Conductor c WHERE c.email = :email")
    , @NamedQuery(name = "Conductor.findByTelefono", query = "SELECT c FROM Conductor c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Conductor.findByUsuario", query = "SELECT c FROM Conductor c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "Conductor.findByPassword", query = "SELECT c FROM Conductor c WHERE c.password = :password")
    , @NamedQuery(name = "Conductor.findByPlacaVehiculo", query = "SELECT c FROM Conductor c WHERE c.placaVehiculo = :placaVehiculo")
    , @NamedQuery(name = "Conductor.findByModeloVehiculo", query = "SELECT c FROM Conductor c WHERE c.modeloVehiculo = :modeloVehiculo")})
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CEDULA")
    private Integer cedula;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Size(max = 15)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 10)
    @Column(name = "PLACA_VEHICULO")
    private String placaVehiculo;
    @Size(max = 50)
    @Column(name = "MODELO_VEHICULO")
    private String modeloVehiculo;
    @JoinTable(name = "PEDIDO_CONDUCTOR", joinColumns = {
        @JoinColumn(name = "ID_CONDUCTOR", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Pedidos> pedidosCollection;

    public Conductor() {
    }

    public Conductor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    @XmlTransient
    public Collection<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(Collection<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
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
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Conductor[ id=" + id + " ]";
    }
    
}
