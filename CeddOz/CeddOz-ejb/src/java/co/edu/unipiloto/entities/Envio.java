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
 * @author david
 */
@Entity
@Table(name = "ENVIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e")
    , @NamedQuery(name = "Envio.findByEnvioid", query = "SELECT e FROM Envio e WHERE e.envioid = :envioid")
    , @NamedQuery(name = "Envio.findByTipoPaquete", query = "SELECT e FROM Envio e WHERE e.tipoPaquete = :tipoPaquete")
    , @NamedQuery(name = "Envio.findByAlto", query = "SELECT e FROM Envio e WHERE e.alto = :alto")
    , @NamedQuery(name = "Envio.findByAncho", query = "SELECT e FROM Envio e WHERE e.ancho = :ancho")
    , @NamedQuery(name = "Envio.findByProdundidad", query = "SELECT e FROM Envio e WHERE e.produndidad = :produndidad")
    , @NamedQuery(name = "Envio.findByPeso", query = "SELECT e FROM Envio e WHERE e.peso = :peso")
    , @NamedQuery(name = "Envio.findByNombreRemitente", query = "SELECT e FROM Envio e WHERE e.nombreRemitente = :nombreRemitente")
    , @NamedQuery(name = "Envio.findByTelefonoRemitente", query = "SELECT e FROM Envio e WHERE e.telefonoRemitente = :telefonoRemitente")
    , @NamedQuery(name = "Envio.findByDireccionRemitente", query = "SELECT e FROM Envio e WHERE e.direccionRemitente = :direccionRemitente")
    , @NamedQuery(name = "Envio.findByEstadoEnvio", query = "SELECT e FROM Envio e WHERE e.estadoEnvio = :estadoEnvio")
    , @NamedQuery(name = "Envio.findByFechaEnvio", query = "SELECT e FROM Envio e WHERE e.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Envio.findByFechaRecibido", query = "SELECT e FROM Envio e WHERE e.fechaRecibido = :fechaRecibido")})
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENVIOID")
    private Integer envioid;
    @Size(max = 40)
    @Column(name = "TIPO_PAQUETE")
    private String tipoPaquete;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTO")
    private Double alto;
    @Column(name = "ANCHO")
    private Double ancho;
    @Column(name = "PRODUNDIDAD")
    private Double produndidad;
    @Column(name = "PESO")
    private Double peso;
    @Size(max = 40)
    @Column(name = "NOMBRE_REMITENTE")
    private String nombreRemitente;
    @Size(max = 30)
    @Column(name = "TELEFONO_REMITENTE")
    private String telefonoRemitente;
    @Size(max = 40)
    @Column(name = "DIRECCION_REMITENTE")
    private String direccionRemitente;
    @Size(max = 40)
    @Column(name = "ESTADO_ENVIO")
    private String estadoEnvio;
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "FECHA_RECIBIDO")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibido;
    @ManyToMany(mappedBy = "envioCollection")
    private Collection<Conductor> conductorCollection;
    @ManyToMany(mappedBy = "envioCollection")
    private Collection<Cliente> clienteCollection;

    public Envio() {
    }

    public Envio(Integer envioid) {
        this.envioid = envioid;
    }

    public Integer getEnvioid() {
        return envioid;
    }

    public void setEnvioid(Integer envioid) {
        this.envioid = envioid;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
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

    public Double getProdundidad() {
        return produndidad;
    }

    public void setProdundidad(Double produndidad) {
        this.produndidad = produndidad;
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

    public String getTelefonoRemitente() {
        return telefonoRemitente;
    }

    public void setTelefonoRemitente(String telefonoRemitente) {
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
        hash += (envioid != null ? envioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envio)) {
            return false;
        }
        Envio other = (Envio) object;
        if ((this.envioid == null && other.envioid != null) || (this.envioid != null && !this.envioid.equals(other.envioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Envio[ envioid=" + envioid + " ]";
    }
    
}
