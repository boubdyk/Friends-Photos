package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesDao implements GenericDao <Devices, String> {

    private EntityManager em = EMFactory.getInstance();

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
//        em.find(Devices.class, transientObject.getIdDevice());
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

    public List<Devices> getAll() {
        TypedQuery<Devices> namedQuery = em.createNamedQuery("Devices.getAll", Devices.class);
        return namedQuery.getResultList();
    }

    public Set<Group> getAllGroupsOfCurrentDevice(String deviceId) {
        Devices obj = read(deviceId);
        return obj.getGroups();
    }

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
        Set<Group> list = read(deviceId).getGroups();
        Iterator<Group> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(groupId)) {
                iterator.remove();
            }
        }
        read(deviceId).setGroups(list);
    }

    public void addGroupToDevice(String deviceId, Group obj) {
        Set<Group> list = read(deviceId).getGroups();
        list.add(obj);
        read(deviceId).setGroups(list);
    }
}