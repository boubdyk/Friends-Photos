package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DevicesDao;
import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesService {
    DevicesDao devicesDao;

    public DevicesService() {
        devicesDao  = new DevicesDao();
    }

    public List<Long> getIdOfAllDevicesInCurrentGroup(String devicesId) {
        List<Long> list = new ArrayList<>();
        for (Group g: devicesDao.getAllGroupsOfCurrentDevice(devicesId)) {
            list.add(g.getId());
        }
        return list;
    }

    public void addNewDevice(Devices obj) {
        devicesDao.create(obj);
    }

    public Devices findById(String id) {
        return devicesDao.read(id);
    }

    public List<Devices> getAllDevices() {
        return devicesDao.getAll();
    }

    public Devices updateDevice(Devices obj) { return devicesDao.update(obj);}

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
            devicesDao.deleteGroupFromDevice(deviceId, groupId);
    }
}
