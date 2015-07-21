package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

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
        em.getTransaction().begin();
        return em.find(Group.class, id);
    }

    @Override
    public Group update(Group transientObject) {
        em.getTransaction().begin();
        em.merge(transientObject);
        em.getTransaction().commit();
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

    public List<Device> getAllDevicesFromGroup(Long groupId) {
        Group obj = read(groupId);
        return obj.getDevices();
    }

    public void deleteDeviceFromGroup(Long groupId, String deviceId) {
        List<Device> list = read(groupId).getDevices();
        Iterator<Device> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdDevice().equals(deviceId)) {
                iterator.remove();
            }
        }
        read(groupId).setDevices(list);
    }

    public void addDeviceToGroup(Long groupId, Device obj) {
        List<Device> list = read(groupId).getDevices();
        list.add(obj);
    }

}
