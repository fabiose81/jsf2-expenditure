package br.com.fabiose.expenditure.dao;

import br.com.fabiose.expenditure.utils.EMFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;
import br.com.fabiose.expenditure.model.ItemTypeModel;

/**
 * Created by fabioestrela on 11/11/16.
 */
public class ItemTypeDao {

    @PersistenceUnit
    private EntityManager entityManager;

    public ItemTypeDao() {
        this.entityManager = EMFactoryUtil.getEntityManagerFactory().createEntityManager();
    }

    public List<ItemTypeModel> getAll() throws Exception {
        try {
            return entityManager.createQuery("from ItemTypeModel", ItemTypeModel.class).getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
     public ItemTypeModel findById(int id) throws Exception {
        try {
            return this.entityManager.find(ItemTypeModel.class, id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void save(ItemTypeModel itemTypeModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(itemTypeModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    public void delete(ItemTypeModel itemTypeModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            itemTypeModel = entityManager.merge(itemTypeModel);
            entityManager.remove(itemTypeModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    public void update(ItemTypeModel itemTypeModel) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(itemTypeModel);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }
}
