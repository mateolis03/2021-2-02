/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edwin
 */
@Entity
@Table(name = "PEDIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")
    , @NamedQuery(name = "Pedidos.findById", query = "SELECT p FROM Pedidos p WHERE p.id = :id")
    , @NamedQuery(name = "Pedidos.findByTipo", query = "SELECT p FROM Pedidos p WHERE p.tipo = :tipo")
    , @NamedQuery(name = "Pedidos.findByAlto", query = "SELECT p FROM Pedidos p WHERE p.alto = :alto")
    , @NamedQuery(name = "Pedidos.findByAncho", query = "SELECT p FROM Pedidos p WHERE p.ancho = :ancho")
    , @NamedQuery(name = "Pedidos.findByProfundidad", query = "SELECT p FROM Pedidos p WHERE p.profundidad = :profundidad")
    , @NamedQuery(name = "Pedidos.findByPeso", query = "SELECT p FROM Pedidos p WHERE p.peso = :peso")
    , @NamedQuery(name = "Pedidos.findByNombreRemitente", query = "SELECT p FROM Pedidos p WHERE p.nombreRemitente = :nombreRemitente")
    , @NamedQuery(name = "Pedidos.findByTelefonoRemitente", query = "SELECT p FROM Pedidos p WHERE p.telefonoRemitente = :telefonoRemitente")
    , @NamedQuery(name = "Pedidos.findByDireccionRemitente", query = "SELECT p FROM Pedidos p WHERE p.direccionRemitente = :direccionRemitente")
    , @NamedQuery(name = "Pedidos.findByEstadoEnvio", query = "SELECT p FROM Pedidos p WHERE p.estadoEnvio = :estadoEnvio")
    , @NamedQuery(name = "Pedidos.findByFechaEnvio", query = "SELECT p FROM Pedidos p WHERE p.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Pedidos.findByFechaRecibido", query = "SELECT p FROM Pedidos p WHERE p.fechaRecibido = :fechaRecibido")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "TIPO")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTO")
    private Double alto;
    @Column(name = "ANCHO")
    private Double ancho;
    @Column(name = "PROFUNDIDAD")
    private Double profundidad;
    @Column(name = "PESO")
    private Double peso;
    @Size(max = 60)
    @Column(name = "NOMBRE_REMITENTE")
    private String nombreRemitente;
    @Column(name = "TELEFONO_REMITENTE")
    private Integer telefonoRemitente;
    @Size(max = 50)
    @Column(name = "DIRECCION_REMITENTE")
    private String direccionRemitente;
    @Size(max = 30)
    @Column(name = "ESTADO_ENVIO")
    private String estadoEnvio;
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "FECHA_RECIBIDO")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibido;
    @ManyToMany(mappedBy = "pedidosCollection")
    private Collection<Conductor> conductorCollection;
    @ManyToMany(mappedBy = "pedidosCollection")
    private Collection<Cliente> clienteCollection;

    public Pedidos() {
    }

    public Pedidos(Integer id, String tipo, Double alto, Double ancho, Double profundidad, Double peso, String nombreRemitente, Integer telefonoRemitente, String direccionRemitente, String estadoEnvio) {
        this.id = id;
        this.tipo = tipo;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.peso = peso;
        this.nombreRemitente = nombreRemitente;
        this.telefonoRemitente = telefonoRemitente;
        this.direccionRemitente = direccionRemitente;
        this.estadoEnvio = estadoEnvio;
    }

    public Pedidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public Integer getTelefonoRemitente() {
        return telefonoRemitente;
    }

    public void setTelefonoRemitente(Integer telefonoRemitente) {
        this.telefonoRemitente = telefonoRemitente;
    }

    public String getDireccionRemitente() {
        return direccionRemitente;
    }

    public void setDireccionRemitente(String direccionRemitente) {
        this.direccionRemitente = direccionRemitente;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @XmlTransient
    public Collection<Conductor> getConductorCollection() {
        return conductorCollection;
    }

    public void setConductorCollection(Collection<Conductor> conductorCollection) {
        this.conductorCollection = conductorCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
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
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Pedidos[ id=" + id + " ]";
    }
    
}
