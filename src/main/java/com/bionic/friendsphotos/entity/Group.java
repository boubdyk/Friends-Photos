package com.bionic.friendsphotos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "creator_id")
    private String creatorId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "devices_in_groups",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id")})
    private List<Devices> devices = new ArrayList<>();

    public Group() {
    }

    public Group(String name, Byte type, String password, String creatorId) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.creatorId = creatorId;
    }

    public Group(Long id, String name, Byte type, String password, String creatorId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.password = password;
        this.creatorId = creatorId;
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

    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        List<String> devicesId = new ArrayList<>();
        for (Devices d: getDevices()) {
            devicesId.add(d.getIdDevice());
        }
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", devices='" + devicesId + '\'' +
                '}';
    }

    public List<Devices> getDevices() {
        return devices;
    }
}
