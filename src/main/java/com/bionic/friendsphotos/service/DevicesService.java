package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 17.07.2015.
 */

@Named
@Transactional
public class DevicesService {

    @Inject
    private DeviceDao devicesDao;

    @Inject
    private GroupDao groupDao;

    public DevicesService() {
//        devicesDao  = new DeviceDao();
//        groupDao = new GroupDao();
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
        devicesDao.addGroupToDevice(deviceId, groupDao.read(groupId));
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to get current group of user and his user-name.
     *
     * @param deviceId Identifier of requested device.
     *
     * @return 1. TreeMap<String, Object> {fieldName -> value of this field};
     *         2. Null if user doesn't exist in Data Base.
     */

    public Map<String, Object> getCurrentGroupAndUserName(String deviceId) {
        Device device = findById(deviceId);
        if (device != null) {
            Map<String, Object> map = new TreeMap<>();
            map.put("currentGroup", device.getCurrentGroup());
            map.put("userName", device.getName());
            return map;
        } else return null;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * Loging by Facebook
     *
     * This method is used to log in user by Facebook.
     *
     * @param deviceId Identifier of requested device
     * @param fbProfile Identifier of Facebook account
     * @param userName Name of requested user
     * @param description Phone model and operation system of device
     *
     * @return boolean Value true if data was put to DB, false if data wasn't put to DB.
     */

    public boolean loginByFacebook(String deviceId, BigInteger fbProfile, String userName, String description) {
        if (deviceId == null) return false;
        if (fbProfile == null) return false;
        if (userName == null) return false;
        if (description == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        if (StringUtils.isEmpty(userName)) return false;
        if (StringUtils.isEmpty(description)) return false;
        Device device = findById(deviceId);
        if (device == null) {
            device = new Device(deviceId, fbProfile, userName);
            device.setDescription(description);
            addNew(device);
        } else {
            device.setFbProfile(fbProfile);
            device.setName(userName);
            updateDevice(device);
        }
        device = devicesDao.read(deviceId);
        if (device == null) return false;
        if (!(device.getFbProfile().equals(fbProfile) && device.getName().equals(userName))) return false;
        return true;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to browse requestedFbProfileList of friends who have FriendsPhoto application on device
     * and all groups in which they participate.
     *
     * @param requestedFbProfileList List of all friends of current user.
     *
     * @return null 1. If input reference is null;
     *              2. If input list is empty;
     *              3. If nobody in list of friends has FriendPhoto application on his device.
     * @return Map of all friends that has FriendsPhoto application and also list of their groups.
     */
    public Map<BigInteger, List<Group>> getAllFriendsOfCurrentUser(List<BigInteger> requestedFbProfileList) {
        if (requestedFbProfileList == null) return null;
        if (requestedFbProfileList.size() == 0) return null;
        List<Device> deviceList = devicesDao.getAll();
        List<Device> mergedList = new ArrayList<>();
        for (int i = 0; i < deviceList.size(); i++) {
            for (int j = 0; j < requestedFbProfileList.size(); j++) {
                if (deviceList.get(i).getFbProfile().equals(requestedFbProfileList.get(j))) {
                    mergedList.add(deviceList.get(i));
                }
            }
        }
        if (mergedList.size() == 0) return null;
        Map<BigInteger, List<Group>> friendsMap = new TreeMap<>();
        for (Device d: mergedList) {
            friendsMap.put(d.getFbProfile(), d.getGroups());
        }
        return friendsMap;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to connect user to chosen group if this group is open.
     * When user want's to connect to the group which
     * is current for him, method returns true.
     *
     * @param deviceId Device unique inedtifier.
     * @param groupId Group unique identifier.
     *
     * @return false 1. If deviceId reference is null;
     *               2. If deficeId is empty;
     *               3. If groupId reference is null;
     *               4. If group doesn't exist;
     *               5. If device doesn't exist;
     * @return true  1. If group that user want to connect is already current for his device;
     *               2. If current group has been changed.
     */
    public boolean connectUserToOpenGroup(String deviceId, Long groupId) {
        if (deviceId == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        if (groupId == null) return false;
        Group group = groupDao.read(groupId);
        if (group == null) return false;
        Device device = findById(deviceId);
        if (device == null) return false;
        if (device.getCurrentGroup().equals(groupId)) return true;
        device.setCurrentGroup(groupId);
        if (!device.getGroups().contains(group)) {
            addGroupToDevice(device.getIdDevice(), group.getId());
        }
        updateDevice(device);
        return true;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to connect to the closed group. It will check password
     * and if it's correct, group will be added to current within requested device
     * and method returns true.
     * If password is incorrect, method returns false.
     *
     * @param deviceId Device unique inedtifier.
     * @param groupId Group unique identifier.
     * @param password Password to closed group.
     *
     * @return false 1. If deviceId reference is null;
     *               2. If groupId reference is null;
     *               3. If password reference is null;
     *               4. If deviceId is empty;
     *               5. If password is empty;
     *               6. If group doesn't exist;
     *               7. If device doesn't exist;
     *               8. If password is incorrect.
     * @return true  1. If group that user want to connect is already current for his device;
     *               2. If password is correct and current group has been changed.
     */
    public boolean connectUserToClosedGroup(String deviceId, Long groupId, String password) {
        if (deviceId == null) return false;
        if (groupId == null) return false;
        if (password == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        if (StringUtils.isEmpty(password)) return false;
        Group group = groupDao.read(groupId);
        if (group == null) return false;
        Device device = findById(deviceId);
        if (device == null) return false;
        if (device.getCurrentGroup().equals(groupId)) return true;
        if (!group.getPassword().equals(password)) return false;
        device.setCurrentGroup(groupId);
        if (!device.getGroups().contains(group)) {
            addGroupToDevice(device.getIdDevice(), group.getId());
        }
        updateDevice(device);
        return true;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method returns list of members of users current group.
     *
     * @param deviceId Device unique inedtifier.
     *
     * @return null 1. If deviceId reference is null;
     *              2. If device doesn't exist.
     * @return List of members of users current group.
     */
    public List<Device> membersOfUsersCurrentGroup(String deviceId) {
        if (deviceId == null) return null;
        Device device = findById(deviceId);
        if (device == null) return null;
        return new ArrayList<>(groupDao.read(device.getCurrentGroup()).getDevices());
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to modify group name. Method returns true if name was modified
     * or return false if user has no admin rights or name wasn't changed.
     *
     * @param deviceId Device unique inedtifier.
     * @param newGroupName New name for users current group.
     *
     * @return false 1. If deviceId reference is null;
     *               2. If newGroupName reference is null;
     *               3. If deviceId is empty;
     *               4. If newGroupName is empty;
     *               5. If device doesn't exist;
     *               6. If this device is not a creator of current group;
     *               7. If group doesn't exist;
     *               8. If name wasn't changed.
     * @return true If name was changed.
     */
    public boolean changeGroupName(String deviceId, String newGroupName) {
        if (deviceId == null) return false;
        if (newGroupName == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        if (StringUtils.isEmpty(newGroupName)) return false;
        Device device = findById(deviceId);
        if (device == null) return false;
        Group group = groupDao.read(device.getCurrentGroup());

        //Only creator can change name
        if (group == null) return false;
        if (!group.getIdCreator().equals(device.getIdDevice())) return false;
        group.setName(newGroupName);
        groupDao.update(group);
        updateDevice(device);
        if (!groupDao.read(device.getCurrentGroup()).getName().equals(newGroupName)) return false;
        return true;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to remove members from current group. Method returns false
     * if user has no admin rights or if members wasn't removed.
     *
     * @param adminId Device unique identifier which has admin rights.
     * @param idDeviceToRemove Device unique identifier which should be removed.
     *
     * @return false 1. If adminId reference is null;
     *               2. If idDeviceToRemove reference is null;
     *               3. If adminId is empty;
     *               4. If idDeviceToRemove is empty;
     *               5. If adminDevice doesn't exist;
     *               6. If deviceToRemove doesn't exist;
     *               7. If group doesn't exist;
     *               8. If adminDevice is not a creator of current group;
     *               9. If device wasn't removed;
     * @return true If device was removed.
     */
    public boolean removeMembersFromCurrentGroup(String adminId, String idDeviceToRemove) {
        if (adminId == null) return false;
        if (idDeviceToRemove == null) return false;
        if (StringUtils.isEmpty(adminId)) return false;
        if (StringUtils.isEmpty(idDeviceToRemove)) return false;
        Device adminDevice = findById(adminId);
        if (adminDevice == null) return false;
        Device deviceToRemove = findById(idDeviceToRemove);
        if (deviceToRemove == null) return false;
        Group group = groupDao.read(adminDevice.getCurrentGroup());

        //Only creator can remove members
        if (group == null) return false;
        if (!group.getIdCreator().equals(adminDevice.getIdDevice())) return false;
        List<Device> members = group.getDevices();
        if (!members.contains(deviceToRemove)) return false;
        members.remove(deviceToRemove);
        if (members.contains(deviceToRemove)) return false;
        List<Group> groupsOfRemovedMember = deviceToRemove.getGroups();
        groupsOfRemovedMember.remove(group);
        groupDao.update(group);
        updateDevice(adminDevice);
        updateDevice(deviceToRemove);
        return !groupsOfRemovedMember.contains(group);
    }

}
