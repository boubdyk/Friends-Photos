package com.bionic.friendsphotos.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private byte type;

    @Column(name = "password")
    private String password;

    @Column(name = "creatorId")
    private String creatorId;

    public Group(int id, String name, byte type, String password, String creatorId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.password = password;
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(byte type) {
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
}
