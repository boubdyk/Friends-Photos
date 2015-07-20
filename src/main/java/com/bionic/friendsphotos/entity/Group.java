package com.bionic.friendsphotos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "\"groups\"")
@NamedQuery(name = "Group.getAll", query = "SELECT c from Group c")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Byte type;

    @Column(name = "pass")
    private String password;

    @Column(name = "id_creator")
    private String idCreator;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "devices_in_groups",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id_device")})
//    @ManyToMany(mappedBy = "groups")
    private Set<Devices> devices;

    public Group() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group(String name, Byte type, String password) {
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public Group(Long id, String name, Byte type, String password) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(String idCreator) {
        this.idCreator = idCreator;
    }

    public Set<Devices> getDevices() {
        return devices;
    }

    public void setDevices(Set<Devices> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        Set<String> devicesId = new HashSet<>();
        for (Devices d: getDevices()) {
            devicesId.add(d.getIdDevice());
        }
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", idCreator='" + idCreator + '\'' +
                ", devices='" + devicesId + '\'' +
                '}';
    }
}
