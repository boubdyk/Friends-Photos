package com.bionic.friendsphotos.modelstructure;

/**
 * Created by c265 on 07.07.2015.
 */
public class TableGroup {
    private int id;
    private String name;
    private byte type;
    private String password;
    private String creatorId;

    public TableGroup(int id, String name, byte type, String password, String creatorId) {
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
