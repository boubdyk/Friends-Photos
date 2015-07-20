package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 14.07.2015.
 */
public class GroupService {

    private static GroupDao groupDao;

    public GroupService() {
        groupDao = new GroupDao();
    }


    public List<Group> getAllGroups() {
        List<Group> groups = groupDao.getAll();
        return groups;
    }

    public Long createGroup(Group obj) {
        try {
            return groupDao.create(obj);
        } catch (IllegalStateException ex) {
            System.out.println("Cannot create group with null fields!");
            return null;
        }
    }

    public Group findById(Long id) {
        return groupDao.read(id);
    }

    public String updateGroup(Group obj) {
        try {
                return "Group" + groupDao.update(obj).getName() + " has been updated!";
        } catch (IllegalArgumentException ex) {
            return "Group" + obj.getName() + " doesn't exist!";
        }
    }

    public String deleteGroup(Long id) {
        try {
            groupDao.delete(id);
            return "Group has been deleted!";
        } catch (IllegalArgumentException ex) {
            return "Group doesn't exist!";
        }
    }

    public String getNameById(Long id) {
        try {
            return groupDao.getNameById(id);
        } catch (NullPointerException ex) {
            return "Group doesn't exist!";
        }
    }

    public List<String> getIdOfAllDevicesInCurrentGroup(Long groupId) {
        List<String> list = new ArrayList<>();
        for (Devices d: groupDao.getAllDevicesFromGroup(groupId)) {
            list.add(d.getIdDevice());
        }
        return list;
    }

//    public List<Devices> setDeviceToGroup(Devices d, Long id) {
//        return groupDao.setDeviceToGroup(d, id);
//    }

    public List<Devices> getAllDevices(Long id) {
        return groupDao.getAllDevicesFromGroup(id);
    }
}
