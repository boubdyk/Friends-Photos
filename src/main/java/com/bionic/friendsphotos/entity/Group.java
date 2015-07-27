package com.bionic.friendsphotos.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Bogdan Sliptsov on 07.07.2015.
 */

@Entity
@Table(name = "\"groups\"")
@NamedQuery(name = "Group.getAll", query = "SELECT g from Group g order by g.id")
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

    @Column(name = "latitude")
    Double latitude;

    @Column(name = "longitude")
    Double longitude;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "devices_in_groups",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id_device")})
//    @ManyToMany(mappedBy = "groups")
    private List<Device> devices;

    public Group() {
    }

    public Group(String name, Byte type, String password, String idCreator, Double latitude, Double longitude, List<Device> devices) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.idCreator = idCreator;
        this.latitude = latitude;
        this.longitude = longitude;
        this.devices = devices;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        Set<String> devicesId = new HashSet<>();
        for (Device d: getDevices()) {
            devicesId.add(d.getIdDevice());
        }
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", idCreator='" + idCreator + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", devices='" + devicesId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (type != null ? !type.equals(group.type) : group.type != null) return false;
        if (password != null ? !password.equals(group.password) : group.password != null) return false;
        if (idCreator != null ? !idCreator.equals(group.idCreator) : group.idCreator != null) return false;
        if (latitude != null ? !latitude.equals(group.latitude) : group.latitude != null) return false;
        if (longitude != null ? !longitude.equals(group.longitude) : group.longitude != null)
            return false;
        return !(devices != null ? !devices.equals(group.devices) : group.devices != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (idCreator != null ? idCreator.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (devices != null ? devices.hashCode() : 0);
        return result;
    }

}
