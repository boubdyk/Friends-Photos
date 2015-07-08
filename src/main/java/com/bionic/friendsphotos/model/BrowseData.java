package com.bionic.friendsphotos.model;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        FriendsPhotoDB data = new FriendsPhotoDB();
        data.createGroup();
        for (TableGroups groups: data.selectAllFromTableGroups()) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }
    }
}
