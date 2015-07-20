package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by c265 on 15.07.2015.
 */

public class GroupDao implements GenericDao <Group, Long> {

    private EntityManager em = EMFactory.getInstance();

    public GroupDao() {
    }

    @Override
    public Long create(Group newInstance) {
        em.getTransaction().begin();
        em.persist(newInstance);
        em.getTransaction().commit();
        return newInstance.getId();
    }


    @Override
    public Group read(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public Group update(Group transientObject) {
        em.find(Group.class, transientObject.getId());
        em.merge(transientObject);
        return transientObject;
    }

    @Override
    public void delete(Long id) {
        Group obj = read(id);
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }

    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }

    public String getNameById(Long id) {
        return em.find(Group.class, id).getName();
    }

    public List<Devices> getAllDevicesFromGroup(Long groupId) {
        Group obj = read(groupId);
        return obj.getDevices();
    }

}
