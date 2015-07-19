package com.bionic.friendsphotos.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "devices")
public class Devices {

    @Id
    @Column(name = "id_device")
    private String idDevice;

    @Column(name = "fb_profile")
    private BigInteger fbProfile;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "devices_in_groups",
            joinColumns = {@JoinColumn(name = "device_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<Group> groups = new ArrayList<>();

    public Devices() {}

    public Devices(String idDevice, BigInteger fbProfile) {
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

    @Override
    public String toString() {
        List<Long> groupId = new ArrayList<>();
        for (Group g: getGroups()) {
            groupId.add(g.getId());
        }
        return "Devices{" +
                "idDevice='" + idDevice + '\'' +
                ", fbProfile=" + fbProfile +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groups=" + groupId +
                '}';
    }
}
