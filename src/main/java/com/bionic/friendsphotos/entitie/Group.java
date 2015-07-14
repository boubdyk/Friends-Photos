package com.bionic.friendsphotos.entitie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

@Entity
@Table(name = "\"groups\"")
public class Group {

    @Id
    /*@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")*/
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Byte type;

    @Column(name = "pass")
    private String password;

    @Column(name = "creator_id")
    private String creatorId;

    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    public Group() {
    }

    public Group(String name, byte type, String password, String creatorId) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.creatorId = creatorId;
    }

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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }
}
