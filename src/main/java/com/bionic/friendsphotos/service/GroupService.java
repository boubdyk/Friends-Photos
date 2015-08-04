package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 14.07.2015.
 */

@Named
@Transactional
public class GroupService {

//    @Inject
    @Autowired
    private GroupDao groupDao;

//    @Inject
    @Autowired
    private DeviceDao devicesDao;

    public GroupService() {
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to get all groups from table "groups" in DB.
     *
     *  @return List<Group> List of all groups.
     */
    public List<Group> getAllGroups() {
        return groupDao.getAll();
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to find group by group id.
     *
     * @param id Unique identifier of group.
     *
     * @return 1. Null If group doesn't exist.
     *         2. Group object If group exists.
     */
    public Group findById(Long id) {
        if (id == null) return null;
        if (groupDao.read(id) == null) return null;
        return groupDao.read(id);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to update group in DB.
     *
     * @param group Group object to update.
     * @return 1. Null If group doesn't exist.
     *         2. Group object of updated group.
     */

    public Group updateGroup(Group group) {
        if (group == null) return null;
        if (groupDao.read(group.getId()) == null) return null;
        return groupDao.update(group);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to delete group from DB if this group exists in DB.
     *
     * @param id Unique group identifier.
     */
    public void deleteGroup(Long id) {
        if (id == null) return;
        if (groupDao.read(id) == null) return;
        groupDao.delete(id);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to get Name of requested group.
     *
     * @param id Unique group identifier.
     * @return 1. Null If group doesn't exist.
     *         2. Name of requested group.
     */
    public String getNameById(Long id) {
        if (id == null) return null;
        if (groupDao.read(id) == null) return null;
        return groupDao.getNameById(id);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to get all devices of requested group.
     *
     * @param id Unique group identifier
     * @return 1. Null If group doesn't exist.
     *         2. List of all devices of requested group.
     */
    public List<Device> getAllDevices(Long id) {
        if (id == null) return null;
        if (groupDao.read(id) == null) return null;
        return groupDao.getAllDevicesFromGroup(id);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to delete device from requested group if group exists;
     * device exists; devices exists in list of devices of requested group.
     *
     * @param groupId Unique group identifier.
     * @param deviceId Unique device identifier.
     *
     */
    public void deleteDeviceFromGroup(Long groupId, String deviceId) {
        if (groupId == null || StringUtils.isEmpty(deviceId)) return;
        if (groupDao.read(groupId) == null) return;
        if (!groupDao.read(groupId).getDevices().contains(devicesDao.read(deviceId))) return;
        groupDao.deleteDeviceFromGroup(groupId, deviceId);
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to add device to requested group if group and device exists.
     * @param groupId Unique group identifier.
     * @param deviceId Unique device identifier.
     */
    public void addDeviceToGroup(Long groupId, String deviceId) {
        if (groupId == null || StringUtils.isEmpty(deviceId)) return;
        if (groupDao.read(groupId) == null) return;
        if (groupDao.read(groupId).getDevices().contains(devicesDao.read(deviceId))) return;
        groupDao.addDeviceToGroup(groupId, devicesDao.read(deviceId));
    }

    /**
     * @author Bogdan Sliptsov
     *
     * User-story, 2 point.
     * User can create group
     *
     * This method is used to create new group.
     *
     * @param groupName Name of new group.
     * @param groupType Type of new group(0 - open, 1 - pass, 2 - moderate)
     * @param password Password of new group. Only if groupType equals 1.
     * @param creatorId Identifier of requested device.
     * @param coordinateX X coordinate of requested device.
     * @param coordinateY Y coordinate of requested device.
     *
     * @return Long This returns unique identifier of just created group.
     *         Null 1. If groupName equals null;
     *              2. If groupType null or != [0, 1, 2];
     *              3. If groupType equals 1 but password is null or empty;
     *              4. If creatorId is null;
     *              5. If requested device doesn't exist.
     *              6. If X or Y coordinate equals null.
     *
     *
     */
    public Long createGroup(String groupName, Byte groupType, String password,
                            String creatorId, Double coordinateX, Double coordinateY) {
        if (StringUtils.isEmpty(groupName)) return null;
        if (groupType == null || groupType < 0 || groupType > 2) return null;
        if (groupType.equals(new Byte(String.valueOf(1))) && StringUtils.isEmpty(password)) return null;
        if (StringUtils.isEmpty(creatorId)) return null;
        if (devicesDao.read(creatorId) == null) return null;
        if (coordinateX == null || coordinateY == null) return null;
        return groupDao.create(new Group(groupName, groupType,
                password, creatorId, coordinateX, coordinateY,
                new ArrayList<Device>(Arrays.asList(devicesDao.read(creatorId)))));

    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to find all groups in DB by group-name or group-id.
     *
     * @param searchValue String search value which can be group-name or group-id
     *
     * @return Null 1. If searchValue reference is null;
     *              2. If searchValue is empty;
     *              3. If there are no groups with such name or id.
     * @exception  NumberFormatException On parsing searchValue to Long (searching by group id)
     */
    public List<Group> getAllGroupsByNameOrID(String searchValue) {
        if (StringUtils.isEmpty(searchValue)) return null;
        List<Group> allGroups = getAllGroups();
        List<Group> resultList = new ArrayList<>();
        for (Group g: allGroups) {
            if (g.getName().equals(searchValue)) {
                resultList.add(g);
                continue;
            }
            try {
                Long id = Long.parseLong(searchValue);
                if (g.getId().equals(id)){
                    resultList.add(g);
                }
            } catch (NumberFormatException ex) { /*NOP*/ }
        }
        if (resultList.isEmpty()) return null;
        return resultList;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * Calculates the distance in km between two lat/long points
     * using the haversine formula
     *
     *
     */
    public double distanceGPS(
            double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the Earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;
        return d;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method is used to find all groups within a radius of 1000 meters.
     *
     * @param latitude Latitude of requested device
     * @param longitude Longitude of requested device
     *
     * @return Null 1. If latitude or longitude is null;
     *              2. If there are no groups wihin radius of 1 km
     *         List of groups within radius of 1 km.
     */
    public List<Group> getAllGroupsByGPS(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) return null;
        List<Group> groups = getAllGroups();
        List<Group> resultList = new ArrayList<>();
        for (Group g: groups) {
            System.out.println(distanceGPS(latitude, longitude, g.getLatitude(), g.getLongitude()));
            if (distanceGPS(latitude, longitude, g.getLatitude(), g.getLongitude()) <= 1.0) {
                resultList.add(g);
            }
        }
        if (resultList.isEmpty()) return null;
        return resultList;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * Returns true if group is open. False if group is closed. //TODO moderate
     */
    public boolean groupIsOpen(Long groupId) {
        if (groupId == null) return false;
        Group group = groupDao.read(groupId);
        if (group == null) return false;
        if (group.getType().equals(new Byte(String.valueOf(1)))) return false;
        return true;
    }

    /**
     * @author Bogdan Sliptsov
     *
     * This method returns true if user is a group-creator and has rights to modify group
     * and returns false if user doesn't have admin rights.
     */
    public boolean isCreator(Long groupId, String deviceId) {
        if (groupId == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        Group group = findById(groupId);
        if (group == null) return false;
        Device device = devicesDao.read(deviceId);
        if (device == null) return false;
        if (!group.getIdCreator().equals(deviceId)) return false;
        return true;
    }

}
