package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.ArticleCommande;

@Stateless
public class ArticleCommandeFacade extends AbstractFacade<ArticleCommande>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public ArticleCommandeFacade() {
        super(ArticleCommande.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
