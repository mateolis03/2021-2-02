package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Envio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T15:30:40")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile CollectionAttribute<Cliente, Envio> envioCollection;
    public static volatile SingularAttribute<Cliente, String> password;
    public static volatile SingularAttribute<Cliente, String> clienteid;
    public static volatile SingularAttribute<Cliente, String> cedula;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> email;

}