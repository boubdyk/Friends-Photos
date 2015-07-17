package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Group;

import java.util.List;

/**
 * Created by c265 on 14.07.2015.
 */
public class GroupService {

    private static GroupDao<Group, Long> groupDao;

    public GroupService() {
       // groupDao = new GroupDao();
        groupDao = new GroupDao(Group.class);
    }


    public List<Group> getAllByBetterDao() {
        List<Group> groups = groupDao.getAll();
        return groups;
    }

    public Group findById(Long id) {
        return groupDao.read(id);
    }

}
