package com.bionic.friendsphotos.entitie;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_device")
    private String idDevice;

    @Column(name = "fb_profile")
    private BigInteger fbProfile;

    public User() {}
    

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_in_groups",
                joinColumns = {@JoinColumn(name = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<Group> groups = new ArrayList<Group>();

    public User(String idDevice, BigInteger fbProfile) {
        this.idDevice = idDevice;
        this.fbProfile = fbProfile;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public BigInteger getFbProfile() {
        return fbProfile;
    }

    public void setFbProfile(BigInteger fbProfile) {
        this.fbProfile = fbProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
