package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by c265 on 17.07.2015.
 */
public class DevicesService {
    DeviceDao devicesDao;
    GroupDao groupDao;

    public DevicesService() {
        devicesDao  = new DeviceDao();
    }

    public void addNew(Device device) {
        devicesDao.create(device);
    }

    public Device findById(String id) {
        return devicesDao.read(id);
    }

    public Device updateDevice(Device obj) { return devicesDao.update(obj);}

    public void deleteDevice(String idDevice) {
        devicesDao.delete(idDevice);
    }

    public List<Long> getIdOfAllGroupsInCurrentDevice(String devicesId) {
        List<Long> list = new ArrayList<>();
        for (Group g: devicesDao.getAllGroupsOfCurrentDevice(devicesId)) {
            list.add(g.getId());
        }
        return list;
    }

    public List<Device> getAll() {
        return devicesDao.getAll();
    }

    public void deleteGroupFromDevice(String deviceId, Long groupId) {
            devicesDao.deleteGroupFromDevice(deviceId, groupId);
    }

    public void addGroupToDevice(String deviceId, Long groupId) {
        groupDao = new GroupDao();
        devicesDao.addGroupToDevice(deviceId, groupDao.read(groupId));
    }

    /**
     * User-story
     * Front-end -> Back-end:
     * Input: deviceId
     * Back-end -> Front-end:
     * Output:
     * method: getCurrentGroupAndUserName returns Map<String, Object> {fieldName -> value of this field};
     * If device doesn't exist method returns null
     */

    public Map<String, Object> getCurrentGroupAndUserName(String deviceId) {
        Device device = findById(deviceId);
        if (device != null) {
            return devicesDao.getCurrentGroupAndUserName(deviceId);
        } else return null;
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
     * @param deviceId identifier of requested device
     * @param fbProfile identifier of Facebook account
     * @param userName name of requested user
     */

    public boolean loginByFacebook(String deviceId, BigInteger fbProfile, String userName) {
        Device device = findById(deviceId);
        if (device == null) {
            device = new Device(deviceId, fbProfile, userName);
            device.setDescription("aiphonia");
            addNew(device);
        } else {
            device.setFbProfile(fbProfile);
            device.setName(userName);
            devicesDao.loginByFacebook(device);
        }
        device = devicesDao.read(deviceId);
        if (device == null) return false;
        if (device.getFbProfile().equals(fbProfile) && device.getName().equals(userName)) return true;
        else return false;
    }

}
