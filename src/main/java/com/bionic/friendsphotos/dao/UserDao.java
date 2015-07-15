package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.User;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by c267 on 15.07.2015.
 */
public class UserDao {


    //Todo: UserDAO class

    public void create(User user) {

    }

    public User read(String device_id){
        return new User("QWE", new BigInteger(String.valueOf(123)));
    }

    public void update(User user){

    }

    public void delete(User user){

    }

    public ArrayList<Group> getGroups(User user){
        return new ArrayList<>();
    }

    public void deleteGroup(User user, Group group){

    }

}
