package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.Conductor;
import co.edu.unipiloto.entities.Registro;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-18T17:50:51")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, String> telefonoDestinatario;
    public static volatile SingularAttribute<Pedidos, String> tipo;
    public static volatile SingularAttribute<Pedidos, String> departamentoDestinatario;
    public static volatile SingularAttribute<Pedidos, Double> peso;
    public static volatile SingularAttribute<Pedidos, String> direccionDestinatario;
    public static volatile CollectionAttribute<Pedidos, Registro> registroCollection;
    public static volatile SingularAttribute<Pedidos, Double> alto;
    public static volatile SingularAttribute<Pedidos, Date> ultimaFecha;
    public static volatile CollectionAttribute<Pedidos, Conductor> conductorCollection;
    public static volatile SingularAttribute<Pedidos, String> ciudadDestinatario;
    public static volatile SingularAttribute<Pedidos, Long> pedidoid;
    public static volatile SingularAttribute<Pedidos, String> nombreDestinatario;
    public static volatile SingularAttribute<Pedidos, String> ultimoEstado;
    public static volatile SingularAttribute<Pedidos, Double> ancho;
    public static volatile SingularAttribute<Pedidos, Double> profundidad;
    public static volatile SingularAttribute<Pedidos, Double> totalPagar;
    public static volatile CollectionAttribute<Pedidos, Cliente> clienteCollection;

}