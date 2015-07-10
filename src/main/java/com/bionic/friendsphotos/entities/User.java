package com.bionic.friendsphotos.entities;

/**
 * Created by c265 on 07.07.2015.
 */
public class User {
    private String idDevice;
    private String fbProfile;

    public User(String idDevice, String fbProfile) {
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
