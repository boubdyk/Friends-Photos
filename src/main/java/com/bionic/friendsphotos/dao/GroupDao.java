package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 15.07.2015.
 */

@Repository
public class GroupDao implements GenericDao <Group, Long> {

    @PersistenceContext(unitName = "entityManager")
//    @PersistenceUnit(unitName = "entityManager")
//    private EntityManagerFactory emf;
    private EntityManager entityManager;/* = emf.createEntityManager();*/

    public GroupDao() {
    }


    @Override
    public Long create(Group newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }


    @Override
    public Group read(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public Group update(Group group) {
        return entityManager.merge(group);
    }

    @Override
    public void delete(Long id) {
        Group obj = read(id);
        entityManager.remove(obj);
    }

    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = entityManager.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }

    public String getNameById(Long id) {
        return entityManager.find(Group.class, id).getName();
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
