package br.com.fabiose.expenditure.utils;

/**
 * Created by fabioestrela on 11/11/16.
 */

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactoryUtil {

    public static EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("expenditureDS");
    }

}
