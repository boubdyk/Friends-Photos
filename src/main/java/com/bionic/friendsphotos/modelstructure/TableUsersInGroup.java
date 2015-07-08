package com.bionic.friendsphotos.modelstructure;

/**
 * Created by c265 on 07.07.2015.
 */
public class TableUsersInGroup {
    private String userDeviceId;
    private  int groupId;

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(String userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
