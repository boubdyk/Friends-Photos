package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesDao implements GenericDao <Devices, String> {

    EntityManager em = Persistence.createEntityManagerFactory("FRIENDSPHOTO").createEntityManager();

    @Override
    public String create(Devices newInstance) {
        em.getTransaction().begin();
        em.persist(newInstance);
        em.getTransaction().commit();
        return newInstance.getIdDevice();
    }

    @Override
    public Devices read(String id) {
        return em.find(Devices.class, id);
    }

    @Override
    public Devices update(Devices transientObject) {
        em.getTransaction().begin();
        em.merge(transientObject);
        em.getTransaction().commit();
        return transientObject;
    }

    @Override
    public void delete(String persistentObject) {
        em.getTransaction().begin();
        em.remove(persistentObject);
        em.getTransaction().commit();
    }

    public List<Group> getAllGroupsOfCurrentDevice(String deviceId) {
        Devices obj = read(deviceId);
        return obj.getGroups();
    }
}
