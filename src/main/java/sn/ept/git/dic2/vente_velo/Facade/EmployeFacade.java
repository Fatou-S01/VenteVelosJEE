package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.vente_velo.entities.Employe;

@Stateless
public class EmployeFacade extends AbstractFacade<Employe> {
    @PersistenceContext(name = "VenteVelo_PU")
    private EntityManager entityManager;

    public EmployeFacade() {
        super(Employe.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
