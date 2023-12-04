package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Commande;

@Stateless
public class CommandeFacade extends AbstractFacade<Commande>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public CommandeFacade() {
        super(Commande.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
