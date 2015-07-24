package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.DeviceDao;
import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Bogdan Sliptsov on 14.07.2015.
 */
public class GroupService {

    private static GroupDao groupDao;
    private static DeviceDao devicesDao;

    public GroupService() {
        groupDao = new GroupDao();
        devicesDao = new DeviceDao();
    }


    public List<Group> getAllGroups() {
        List<Group> groups = groupDao.getAll();
        return groups;
    }

   /* public Long createGroup(Group group) {
        try {
            if (StringUtils.isEmpty(group.getName())) {
                return null;
            }
            return groupDao.create(group);
        } catch (IllegalStateException ex) {
            System.out.println("Cannot create group with null fields!");
            return null;
        }
    }*/

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
        for (Device d: groupDao.getAllDevicesFromGroup(groupId)) {
            list.add(d.getIdDevice());
        }
        return list;
    }

    public List<Device> getAllDevices(Long id) {
        return groupDao.getAllDevicesFromGroup(id);
    }

    public void deleteDeviceFromGroup(Long groupId, String deviceId) {
        groupDao.deleteDeviceFromGroup(groupId, deviceId);
    }

    public void addDeviceToGroup(Long groupId, String deviceId) {
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
     * @return null 1. If groupName equals null;
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
     * @return null 1. If searchValue reference is null;
     *              2. If searchValue is empty;
     *              3. If there are no groups with such name or id.
     * @exception  NumberFormatException On parsing searchValue to Long (searching by group id)
     */
    public List<Group> getAllGroupsByNameOrID(String searchValue) {
        if (searchValue == null) return null;
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
        if (resultList.size() == 0) return null;
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
     * @return null 1. If latitude or longitude is null;
     *              2. If there are no groups wihin radius of 1 km
     * @return List of groups within radius of 1 km.
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
        if (resultList.size() == 0) return null;
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
        if (deviceId == null) return false;
        if (StringUtils.isEmpty(deviceId)) return false;
        Group group = findById(groupId);
        if (group == null) return false;
        Device device = devicesDao.read(deviceId);
        if (device == null) return false;
        if (!group.getIdCreator().equals(deviceId)) return false;
        return true;
    }

}
