package sn.ept.git.dic2.vente_velo.Facade;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Marque;

@Stateless
public class MarqueFacade extends AbstractFacade<Marque>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public MarqueFacade() {
        super(Marque.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
