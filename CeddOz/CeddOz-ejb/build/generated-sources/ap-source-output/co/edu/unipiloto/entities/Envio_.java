package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Cliente;
import co.edu.unipiloto.entities.Conductor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T15:30:40")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, String> nombreRemitente;
    public static volatile SingularAttribute<Envio, Double> peso;
    public static volatile SingularAttribute<Envio, String> estadoEnvio;
    public static volatile SingularAttribute<Envio, Double> alto;
    public static volatile CollectionAttribute<Envio, Conductor> conductorCollection;
    public static volatile SingularAttribute<Envio, String> direccionRemitente;
    public static volatile SingularAttribute<Envio, Date> fechaEnvio;
    public static volatile SingularAttribute<Envio, Date> fechaRecibido;
    public static volatile SingularAttribute<Envio, Integer> envioid;
    public static volatile SingularAttribute<Envio, String> tipoPaquete;
    public static volatile SingularAttribute<Envio, Double> produndidad;
    public static volatile SingularAttribute<Envio, Double> ancho;
    public static volatile SingularAttribute<Envio, String> telefonoRemitente;
    public static volatile CollectionAttribute<Envio, Cliente> clienteCollection;

}