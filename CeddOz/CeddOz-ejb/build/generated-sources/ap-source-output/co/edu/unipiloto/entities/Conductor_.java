package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Envio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-20T15:30:40")
@StaticMetamodel(Conductor.class)
public class Conductor_ { 

    public static volatile CollectionAttribute<Conductor, Envio> envioCollection;
    public static volatile SingularAttribute<Conductor, String> password;
    public static volatile SingularAttribute<Conductor, String> conductorid;
    public static volatile SingularAttribute<Conductor, String> cedula;
    public static volatile SingularAttribute<Conductor, String> telefono;
    public static volatile SingularAttribute<Conductor, String> nombre;
    public static volatile SingularAttribute<Conductor, String> email;

}