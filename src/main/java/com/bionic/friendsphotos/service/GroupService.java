package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entity.Group;

import java.util.List;

/**
 * Created by c265 on 14.07.2015.
 */
public class GroupService {

    private static GroupDao groupDao;

    public GroupService() {
        groupDao = new GroupDao();
    }

    public void persist(Group entity) {
        groupDao.persist(entity);
    }

    public void update(Group entity) {
        groupDao.update(entity);
    }

    public Group findById(Integer id) {
        Group group = groupDao.read(id);
        return group;
    }

    public void delete(Integer id) {
        Group group = groupDao.read(id);
        groupDao.delete(group);
    }

    public List<Group> getAll() {
        List<Group> groups = groupDao.getAll();
        return groups;
    }

    public void deleteAll() {
        groupDao.deleteAll();
    }

    public GroupDao groupDao() {
        return groupDao;
    }
}
