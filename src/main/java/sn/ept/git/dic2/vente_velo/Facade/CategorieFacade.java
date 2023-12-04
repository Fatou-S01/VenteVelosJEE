package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Categorie;

@Stateless
public class CategorieFacade extends AbstractFacade<Categorie>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public CategorieFacade() {
        super(Categorie.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
