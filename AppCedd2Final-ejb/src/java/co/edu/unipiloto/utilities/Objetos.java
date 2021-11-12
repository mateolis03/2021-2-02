/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.utilities;

import co.edu.unipiloto.entities.Pedidos;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author edwin
 */
public class Objetos {
    private Collection<Pedidos> pedidosAll;
    private int size;

    public Objetos() {
        this.pedidosAll = new ArrayList();
        this.size = 0;
    }

    
    public Objetos(Collection<Pedidos> pedidosAll, int size) {
        this.pedidosAll = pedidosAll;
        this.size = size;
    }

    public Collection<Pedidos> getPedidosAll() {
        return pedidosAll;
    }

    public void setPedidosAll(Collection<Pedidos> pedidosAll) {
        this.pedidosAll = pedidosAll;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
