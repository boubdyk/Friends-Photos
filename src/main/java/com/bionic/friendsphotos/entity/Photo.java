package com.bionic.friendsphotos.entity;

import javax.persistence.*;

/**
 * Created by Yevhenii Semenov on 21.07.2015.
 */

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "name")
    private String name;

    public Photo(Long id, Long groupId, String ownerId, String name) {
        this.id = id;
        this.groupId = groupId;
        this.ownerId = ownerId;
        this.name = name;
    }

    public Photo(Long groupId, String ownerId, String name) {
        this.groupId = groupId;
        this.ownerId = ownerId;
        this.name = name;
    }

    public Photo(){}

    public Long getId() {
        return id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
