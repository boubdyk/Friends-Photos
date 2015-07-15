package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by c265 on 15.07.2015.
 */

//@Repository
public class GroupDao1 {

//    @PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory("FRIENDSPHOTO").createEntityManager();

    public GroupDao1() {
    }

    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }


}
