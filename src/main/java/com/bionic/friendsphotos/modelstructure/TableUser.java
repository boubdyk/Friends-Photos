package com.bionic.friendsphotos.modelstructure;

/**
 * Created by c265 on 07.07.2015.
 */
public class TableUser {
    private String idDevice;
    private String fbProfile;

    public TableUser(String idDevice, String fbProfile) {
        this.idDevice = idDevice;
        this.fbProfile = fbProfile;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getFbProfile() {
        return fbProfile;
    }

    public void setFbProfile(String fbProfile) {
        this.fbProfile = fbProfile;
    }
}
