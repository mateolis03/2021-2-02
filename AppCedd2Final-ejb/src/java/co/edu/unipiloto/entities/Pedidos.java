/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    , @NamedQuery(name = "Pedidos.findByPedidoid", query = "SELECT p FROM Pedidos p WHERE p.pedidoid = :pedidoid")
    , @NamedQuery(name = "Pedidos.findByTipo", query = "SELECT p FROM Pedidos p WHERE p.tipo = :tipo")
    , @NamedQuery(name = "Pedidos.findByAlto", query = "SELECT p FROM Pedidos p WHERE p.alto = :alto")
    , @NamedQuery(name = "Pedidos.findByAncho", query = "SELECT p FROM Pedidos p WHERE p.ancho = :ancho")
    , @NamedQuery(name = "Pedidos.findByProfundidad", query = "SELECT p FROM Pedidos p WHERE p.profundidad = :profundidad")
    , @NamedQuery(name = "Pedidos.findByPeso", query = "SELECT p FROM Pedidos p WHERE p.peso = :peso")
    , @NamedQuery(name = "Pedidos.findByNombreDestinatario", query = "SELECT p FROM Pedidos p WHERE p.nombreDestinatario = :nombreDestinatario")
    , @NamedQuery(name = "Pedidos.findByTelefonoDestinatario", query = "SELECT p FROM Pedidos p WHERE p.telefonoDestinatario = :telefonoDestinatario")
    , @NamedQuery(name = "Pedidos.findByCiudadDestinatario", query = "SELECT p FROM Pedidos p WHERE p.ciudadDestinatario = :ciudadDestinatario")
    , @NamedQuery(name = "Pedidos.findByDepartamentoDestinatario", query = "SELECT p FROM Pedidos p WHERE p.departamentoDestinatario = :departamentoDestinatario")
    , @NamedQuery(name = "Pedidos.findByDireccionDestinatario", query = "SELECT p FROM Pedidos p WHERE p.direccionDestinatario = :direccionDestinatario")
    , @NamedQuery(name = "Pedidos.findByUltimoEstado", query = "SELECT p FROM Pedidos p WHERE p.ultimoEstado = :ultimoEstado")
    , @NamedQuery(name = "Pedidos.findByTotalPagar", query = "SELECT p FROM Pedidos p WHERE p.totalPagar = :totalPagar")
    , @NamedQuery(name = "Pedidos.findByUltimaFecha", query = "SELECT p FROM Pedidos p WHERE p.ultimaFecha = :ultimaFecha")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEDIDOID")
    private Long pedidoid;
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
    @Size(max = 50)
    @Column(name = "NOMBRE_DESTINATARIO")
    private String nombreDestinatario;
    @Size(max = 15)
    @Column(name = "TELEFONO_DESTINATARIO")
    private String telefonoDestinatario;
    @Size(max = 20)
    @Column(name = "CIUDAD_DESTINATARIO")
    private String ciudadDestinatario;
    @Size(max = 20)
    @Column(name = "DEPARTAMENTO_DESTINATARIO")
    private String departamentoDestinatario;
    @Size(max = 40)
    @Column(name = "DIRECCION_DESTINATARIO")
    private String direccionDestinatario;
    @Size(max = 15)
    @Column(name = "ULTIMO_ESTADO")
    private String ultimoEstado;
    @Column(name = "TOTAL_PAGAR")
    private Double totalPagar;
    @Column(name = "ULTIMA_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFecha;
    @ManyToMany(mappedBy = "pedidosCollection")
    private Collection<Cliente> clienteCollection;
    @ManyToMany(mappedBy = "pedidosCollection")
    private Collection<Conductor> conductorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoId")
    private Collection<Registro> registroCollection;

    public Pedidos() {
    }

    public Pedidos(Long pedidoid) {
        this.pedidoid = pedidoid;
    }

    public Pedidos(Long pedidoid, String tipo, Double alto, Double ancho, Double profundidad, Double peso, String nombreDestinatario, String telefonoDestinatario, String ciudadDestinatario, String departamentoDestinatario, String direccionDestinatario, String ultimoEstado, Date ultimaFecha, Double totalPagar) {
        this.pedidoid = pedidoid;
        this.tipo = tipo;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.peso = peso;
        this.nombreDestinatario = nombreDestinatario;
        this.telefonoDestinatario = telefonoDestinatario;
        this.ciudadDestinatario = ciudadDestinatario;
        this.departamentoDestinatario = departamentoDestinatario;
        this.direccionDestinatario = direccionDestinatario;
        this.ultimoEstado = ultimoEstado;
        this.totalPagar = totalPagar;
        this.ultimaFecha = ultimaFecha;
        this.registroCollection = new ArrayList();
        
    }
    
    

    public Long getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(Long pedidoid) {
        this.pedidoid = pedidoid;
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

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getTelefonoDestinatario() {
        return telefonoDestinatario;
    }

    public void setTelefonoDestinatario(String telefonoDestinatario) {
        this.telefonoDestinatario = telefonoDestinatario;
    }

    public String getCiudadDestinatario() {
        return ciudadDestinatario;
    }

    public void setCiudadDestinatario(String ciudadDestinatario) {
        this.ciudadDestinatario = ciudadDestinatario;
    }

    public String getDepartamentoDestinatario() {
        return departamentoDestinatario;
    }

    public void setDepartamentoDestinatario(String departamentoDestinatario) {
        this.departamentoDestinatario = departamentoDestinatario;
    }

    public String getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(String direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Date getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(Date ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Conductor> getConductorCollection() {
        return conductorCollection;
    }

    public void setConductorCollection(Collection<Conductor> conductorCollection) {
        this.conductorCollection = conductorCollection;
    }

    @XmlTransient
    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoid != null ? pedidoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.pedidoid == null && other.pedidoid != null) || (this.pedidoid != null && !this.pedidoid.equals(other.pedidoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.entities.Pedidos[ pedidoid=" + pedidoid + " ]";
    }
    
}
