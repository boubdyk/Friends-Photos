package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesService {
    DeviceDao devicesDao;
    GroupDao groupDao;

    public DevicesService() {
        devicesDao  = new DeviceDao();
    }

    public void addNew(Device obj) {
        devicesDao.create(obj);
    }

    public Device findById(String id) {
        return devicesDao.read(id);
    }

    public Device updateDevice(Device obj) { return devicesDao.update(obj);}

    public void deleteDevice(String idDevice) {
        devicesDao.delete(idDevice);
    }

    public Set<Long> getIdOfAllGroupsInCurrentDevice(String devicesId) {
        Set<Long> list = new HashSet<>();
        for (Group g: devicesDao.getAllGroupsOfCurrentDevice(devicesId)) {
            list.add(g.getId());
        }
        return list;
    }

    public Set<Device> getAll() {
        return devicesDao.getAll();
    }

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
            devicesDao.deleteGroupFromDevice(deviceId, groupId);
    }

    public void addGroupToDevice(String deviceId, Long groupId) {
        groupDao = new GroupDao();
        devicesDao.addGroupToDevice(deviceId, groupDao.read(groupId));
    }

}
