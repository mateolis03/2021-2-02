package co.edu.unipiloto.entities;

import co.edu.unipiloto.entities.Pedidos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-18T17:50:51")
@StaticMetamodel(Registro.class)
public class Registro_ { 

    public static volatile SingularAttribute<Registro, String> descripcion;
    public static volatile SingularAttribute<Registro, Date> fecha;
    public static volatile SingularAttribute<Registro, String> estado;
    public static volatile SingularAttribute<Registro, Pedidos> pedidoId;
    public static volatile SingularAttribute<Registro, String> ciudad;
    public static volatile SingularAttribute<Registro, String> departamento;
    public static volatile SingularAttribute<Registro, Long> registroid;

}