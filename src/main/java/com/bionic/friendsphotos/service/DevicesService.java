package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesService {
    DeviceDao devicesDao;
    GroupDao groupDao;

    public DevicesService() {
        devicesDao  = new DeviceDao();
    }

    public List<Long> getIdOfAllGroupsInCurrentDevice(String devicesId) {
        List<Long> list = new ArrayList<>();
        for (Group g: devicesDao.getAllGroupsOfCurrentDevice(devicesId)) {
            list.add(g.getId());
        }
        return list;
    }

    public void addNew(Device obj) {
        devicesDao.create(obj);
    }

    public Device findById(String id) {
        return devicesDao.read(id);
    }

    public List<Device> getAll() {
        return devicesDao.getAll();
    }

    public Device updateDevice(Device obj) { return devicesDao.update(obj);}

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
            devicesDao.deleteGroupFromDevice(deviceId, groupId);
    }

    public void addGroupToDevice(String deviceId, Long groupId) {
        groupDao = new GroupDao();
        devicesDao.addGroupToDevice(deviceId, groupDao.read(groupId));
    }

}
