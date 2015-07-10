package com.bionic.friendsphotos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "users")
public class User {

    @Column(name = "id_device")
    private String idDevice;

    @Column(name = "fb_profile")
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
