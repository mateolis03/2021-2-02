/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.entities;

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
 * @author edwin
 */
@Entity
@Table(name = "REGISTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByRegistroid", query = "SELECT r FROM Registro r WHERE r.registroid = :registroid")
    , @NamedQuery(name = "Registro.findByCiudad", query = "SELECT r FROM Registro r WHERE r.ciudad = :ciudad")
    , @NamedQuery(name = "Registro.findByDepartamento", query = "SELECT r FROM Registro r WHERE r.departamento = :departamento")
    , @NamedQuery(name = "Registro.findByFecha", query = "SELECT r FROM Registro r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Registro.findByDescripcion", query = "SELECT r FROM Registro r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Registro.findByEstado", query = "SELECT r FROM Registro r WHERE r.estado = :estado")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTROID")
    private Long registroid;
    @Size(max = 20)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Size(max = 20)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "PEDIDOID")
    @ManyToOne(optional = false)
    private Pedidos pedidoId;

    public Registro() {
    }

    public Registro(Long registroid) {
        this.registroid = registroid;
    }

    public Registro(Long registroid, String ciudad, String departamento, Date fecha, String descripcion, String estado, Pedidos pedidoId) {
        this.registroid = registroid;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.pedidoId = pedidoId;
    }

    
    public Long getRegistroid() {
        return registroid;
    }

    public void setRegistroid(Long registroid) {
        this.registroid = registroid;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pedidos getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedidos pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroid != null ? registroid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.registroid == null && other.registroid != null) || (this.registroid != null && !this.registroid.equals(other.registroid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Registro[ registroid=" + registroid + " ]";
    }
    
}
