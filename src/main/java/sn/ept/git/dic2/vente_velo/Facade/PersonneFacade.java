package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Marque;
import sn.ept.git.dic2.vente_velo.entities.Personne;

@Stateless
public class PersonneFacade extends AbstractFacade<Personne>{
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public PersonneFacade() {
        super(Personne.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
