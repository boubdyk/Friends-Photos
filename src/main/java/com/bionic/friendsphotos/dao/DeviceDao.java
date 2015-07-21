package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by c265 on 17.07.2015.
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
        return em.find(Device.class, id);
    }

    @Override
    public Device update(Device transientObject) {
//        em.find(Devices.class, transientObject.getIdDevice());
        em.getTransaction().begin();
        em.merge(transientObject);
        em.getTransaction().commit();
        return transientObject;
    }

    @Override
    public void delete(String idDevice) {
        em.getTransaction().begin();
        em.remove(read(idDevice));
        em.getTransaction().commit();
    }

    public Set<Device> getAll() {
        TypedQuery<Device> namedQuery = em.createNamedQuery("Device.getAll", Device.class);
        return new HashSet<>(namedQuery.getResultList());
    }

    public Set<Group> getAllGroupsOfCurrentDevice(String deviceId) {
        Device obj = read(deviceId);
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
