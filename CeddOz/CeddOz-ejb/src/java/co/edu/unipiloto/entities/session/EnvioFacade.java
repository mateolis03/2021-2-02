/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.entities.session;

import co.edu.unipiloto.entities.Envio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author david
 */
@Stateless
public class EnvioFacade extends AbstractFacade<Envio> implements EnvioFacadeLocal {

    @PersistenceContext(unitName = "CeddOz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnvioFacade() {
        super(Envio.class);
    }
    
}
