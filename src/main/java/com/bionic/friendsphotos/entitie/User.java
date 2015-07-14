package com.bionic.friendsphotos.entitie;

import javax.persistence.*;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
//@Embeddable
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_device")
    private String idDevice;

    @Column(name = "fb_profile")
    private String fbProfile;

    public User() {}

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
