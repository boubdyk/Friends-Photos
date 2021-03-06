package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 17.07.2015.
 */
public class DeviceDao implements GenericDao <Device, String> {

    private EntityManager em = EMFactory.getInstance();

    @Override
    public String create(Device newInstance) {
        em.getTransaction().begin();
        em.persist(newInstance);
        em.getTransaction().commit();
        return newInstance.getIdDevice();
    }

    @Override
    public Device read(String id) {
//        em.getTransaction().begin();
        return em.find(Device.class, id);
    }

    @Override
    public Device update(Device transientObject) {
        em.getTransaction().begin();
        em.merge(transientObject);
        em.getTransaction().commit();
        return transientObject;
    }

    @Override
    public void delete(String idDevice) {
//        em.getTransaction().begin();
        em.remove(read(idDevice));
//        em.getTransaction().commit();
    }

    public List<Device> getAll() {
        TypedQuery<Device> namedQuery = em.createNamedQuery("Device.getAll", Device.class);
        return namedQuery.getResultList();
    }

    public List<Group> getAllGroupsOfCurrentDevice(String deviceId) {
        Device obj = read(deviceId);
        return obj.getGroups();
    }

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
        List<Group> list = read(deviceId).getGroups();
        Iterator<Group> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(groupId)) {
                iterator.remove();
            }
        }
        read(deviceId).setGroups(list);
    }

    public void addGroupToDevice(String deviceId, Group group) {
        List<Group> list = read(deviceId).getGroups();
        list.add(group);
        read(deviceId).setGroups(list);
    }



    /**
     * Loging by Facebook
     *
     * Front-end -> Back-end
     * Input: deviceId, fbProfile, userName
     *
     * Back-end -> Front-end
     * Output: returns boolean: true/false
     * ***************************************************************************************
     * Returns boolean value true if data was put to DB, false if data wasn't put to DB
     *
     * @param device device to update
     */


}
