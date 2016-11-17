/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import br.com.fabiose.expenditure.dto.ExpenditureDto;
import br.com.fabiose.expenditure.utils.EMFactoryUtil;
import br.com.fabiose.expenditure.utils.DateUtil;
import java.util.ArrayList;

/**
 *
 * @author fabioestrela
 */

public class ExpenditureDao {

    @PersistenceUnit
    private EntityManager entityManager;

    public ExpenditureDao() {
        entityManager = EMFactoryUtil.getEntityManagerFactory().createEntityManager();
    }

    public List<ExpenditureDto> getExpenditureByDay(Date date) throws Exception {
        try {
            String query = "select new br.com.fabiose.expenditure.dto.ExpenditureDto(sum(i.value), it.name) from ItemModel i "
                    + "join i.itemType it "
                    + "where to_char(i.date,'dd/MM/yyyy') = :date group by it.name";

            return entityManager.createQuery(query)
                    .setParameter("date", DateUtil.formatDate(date, "dd/MM/yyyy"))
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ExpenditureDto> getExpenditureByMonth(Date date) throws Exception {
        try {
            String query = "select new br.com.fabiose.expenditure.dto.ExpenditureDto(sum(i.value), it.name) from ItemModel i "
                    + "join i.itemType it "
                    + "where to_char(i.date,'MM') = :date group by it.name";

            return entityManager.createQuery(query)
                    .setParameter("date", DateUtil.formatDate(date, "MM"))
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ExpenditureDto> getExpenditureByYear(Date date) throws Exception {
        try {
            String query = "select new br.com.fabiose.expenditure.dto.ExpenditureDto(sum(i.value), it.name) from ItemModel i "
                    + "join i.itemType it "
                    + "where to_char(i.date,'yyyy') = :date group by it.name";

            return entityManager.createQuery(query)
                    .setParameter("date", DateUtil.formatDate(date, "yyyy"))
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
