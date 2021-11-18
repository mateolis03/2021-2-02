package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Pedidos;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-18T17:50:51")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> password;
    public static volatile SingularAttribute<Cliente, Long> clienteid;
    public static volatile SingularAttribute<Cliente, BigInteger> cedula;
    public static volatile SingularAttribute<Cliente, String> ciudad;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> departamento;
    public static volatile SingularAttribute<Cliente, String> usuario;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile CollectionAttribute<Cliente, Pedidos> pedidosCollection;

}