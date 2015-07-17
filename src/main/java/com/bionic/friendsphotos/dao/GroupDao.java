package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by c265 on 15.07.2015.
 */

public class GroupDao <T, PK extends Serializable>
        implements GenericDao <T, PK> {

    private Class<T> type;

    private EntityManager em = Persistence.createEntityManagerFactory("FRIENDSPHOTO").createEntityManager();

    public GroupDao(Class<T> type) {
        this.type = type;
    }

    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }


    @Override
    public PK create(T newInstance) {
        return null;
    }

    @Override
    public T read(PK id) {
        return em.find(type, id);
    }

    @Override
    public void update(T transientObject) {

    }

    @Override
    public void delete(T persistentObject) {

    }
}
