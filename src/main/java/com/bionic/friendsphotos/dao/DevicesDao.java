package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Devices;

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

    List<Devices> getAllDevicesFromGroup(Long groupId) {
        String query = "SELECT d.device_id FROM devices_in_groups d where group_id=" + groupId + ";";
        return em.createQuery(query).getResultList();
    }
}
