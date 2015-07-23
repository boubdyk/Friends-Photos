package com.bionic.friendsphotos.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "devices")
@NamedQuery(name = "Device.getAll", query = "SELECT d from Device d")
public class Device {

    @Id
    @Column(name = "id_device")
    private String idDevice;

    @Column(name = "fb_profile")
    private BigInteger fbProfile;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    String description;

    @Column(name = "current_group")
    Long currentGroup;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "devices_in_groups",
            joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id_device")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
    private List<Group> groups;

    public Device() {}

    public Device(String idDevice, BigInteger fbProfile, String name) {
        this.idDevice = idDevice;
        this.fbProfile = fbProfile;
        this.name = name;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Long currentGroup) {
        this.currentGroup = currentGroup;
    }

    @Override
    public String toString() {
        Set<Long> groupId = new HashSet<>();
        for (Group g: getGroups()) {
            groupId.add(g.getId());
        }
        return "Devices{" +
                "idDevice='" + idDevice + '\'' +
                ", fbProfile=" + fbProfile +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentGroup='" + currentGroup + '\'' +
                ", groups=" + groupId +
                '}';
    }
}
