package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Magasin;

@Stateless
public class MagasinFacade extends AbstractFacade<Magasin>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public MagasinFacade() {
        super(Magasin.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
