package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Pedidos;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-18T17:50:51")
@StaticMetamodel(Conductor.class)
public class Conductor_ { 

    public static volatile SingularAttribute<Conductor, String> password;
    public static volatile SingularAttribute<Conductor, String> placaVehiculo;
    public static volatile SingularAttribute<Conductor, Long> conductorid;
    public static volatile SingularAttribute<Conductor, String> modeloVehiculo;
    public static volatile SingularAttribute<Conductor, BigInteger> cedula;
    public static volatile SingularAttribute<Conductor, String> usuario;
    public static volatile SingularAttribute<Conductor, String> telefono;
    public static volatile SingularAttribute<Conductor, String> nombre;
    public static volatile SingularAttribute<Conductor, String> email;
    public static volatile CollectionAttribute<Conductor, Pedidos> pedidosCollection;

}