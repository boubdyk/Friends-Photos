package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 15.07.2015.
 */

@Repository
public class GroupDao implements GenericDao <Group, Long> {

    @PersistenceContext
    private EntityManager em;

    public GroupDao() {
    }


    @Override
    public Long create(Group newInstance) {
        em.persist(newInstance);
        return newInstance.getId();
    }


    @Override
    public Group read(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public Group update(Group group) {
        return em.merge(group);
    }

    @Override
    public void delete(Long id) {
        Group obj = read(id);
        em.remove(obj);
    }

    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }

    public String getNameById(Long id) {
        return em.find(Group.class, id).getName();
    }

    public List<Device> getAllDevicesFromGroup(Long groupId) {
        return read(groupId).getDevices();
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
