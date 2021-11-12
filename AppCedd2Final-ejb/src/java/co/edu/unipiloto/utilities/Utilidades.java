/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.utilities;

import co.edu.unipiloto.entities.Conductor;
import java.util.Collection;

/**
 *
 * @author edwin
 */
public class Utilidades {

    public static String passGenerator(int length) {
        String NUMEROS = "0123456789";

        String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

        String ESPECIALES = "ñÑ";

        String key = NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES;
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;

    }
    
    /*public static Conductor verDisponibilidad(Collection<Conductor> conductores){
        Conductor temp = new Conductor();
        boolean init = true;
        for(Conductor a: conductores){
            if(init){
                temp = a;
                init = false;
            }else{
                if(a.getPedidosCollection().size() < temp.getPedidosCollection().size()){
                    temp = a;
                }
            }
        }
        return temp;
    }*/
}
