package br.com.fabiose.expenditure.dao;

/**
 * Created by fabioestrela on 11/11/16.
 */
import br.com.fabiose.expenditure.utils.EMFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;
import br.com.fabiose.expenditure.model.ItemModel;

public class ItemDao {

    @PersistenceUnit
    private EntityManager entityManager;

    public ItemDao() {
        entityManager = EMFactoryUtil.getEntityManagerFactory().createEntityManager();
    }

    public List<ItemModel> getAll() throws Exception{
        try {
            return entityManager.createQuery("select i from ItemModel i ").getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ItemModel findById(int id) throws Exception {
        try {
            return this.entityManager.find(ItemModel.class, id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void save(ItemModel itemModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(itemModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    public void delete(ItemModel itemModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            itemModel = entityManager.merge(itemModel);
            entityManager.remove(itemModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    public void update(ItemModel itemModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(itemModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

}
