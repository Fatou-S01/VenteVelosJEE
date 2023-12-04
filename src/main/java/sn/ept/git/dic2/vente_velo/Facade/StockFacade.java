package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Produit;
import sn.ept.git.dic2.vente_velo.entities.Stock;

@Stateless
public class StockFacade extends AbstractFacade<Stock>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public StockFacade() {
        super(Stock.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
