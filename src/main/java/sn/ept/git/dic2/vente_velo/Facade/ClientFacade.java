package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public ClientFacade() {
        super(Client.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
