package sn.ept.git.dic2.vente_velo.Facade;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;

import java.util.List;

//facade générique
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public T findTwoId(Object id1, Object id2) {
        T entity1 = getEntityManager().find(entityClass, id1);
        T entity2 = getEntityManager().find(entityClass, id2);
        return entity1;
    }



    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void removeAll(List<T> entities) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Obtenez la classe d'entité
        //Class<T> entityClass = getEntityClass();

        // Créez une clause DELETE à l'aide de Criteria API
        CriteriaDelete<T> delete = cb.createCriteriaDelete(entityClass);
        Root<T> root = delete.from(entityClass);

        // Ajoutez une condition pour supprimer les entités dans la liste
        delete.where(root.in(entities));

        // Exécutez la requête DELETE
        entityManager.createQuery(delete).executeUpdate();
    }

}

