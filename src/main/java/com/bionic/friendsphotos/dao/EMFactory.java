package com.bionic.friendsphotos.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by c265 on 20.07.2015.
 */
public class EMFactory {
    private static final EntityManager em = Persistence.createEntityManagerFactory("FRIENDSPHOTO").createEntityManager();

    private EMFactory(){}

    public static EntityManager getInstance() {
        return em;
    }

}
