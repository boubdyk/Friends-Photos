package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.GroupDao;
import com.bionic.friendsphotos.entitie.Group;

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
        groupDao.openCurrentSessionWithTransaction();
        groupDao.persist(entity);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public void update(Group entity) {
        groupDao.openCurrentSessionWithTransaction();
        groupDao.update(entity);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public Group findById(String id) {
        groupDao.openCurrentSession();
        Group group = groupDao.read(id);
        groupDao.closeCurrentSession();
        return group;
    }

    public void delete(String id) {
        groupDao.openCurrentSessionWithTransaction();
        Group group = groupDao.read(id);
        groupDao.delete(group);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public List<Group> findAll() {
        groupDao.openCurrentSession();
        List<Group> groups = groupDao.getAll();
        groupDao.closeCurrentSession();
        return groups;
    }

    public void deleteAll() {
        groupDao.openCurrentSessionWithTransaction();
        groupDao.deleteAll();
        groupDao.closeCurrentSessionwithTransaction();
    }

    public GroupDao bookDao() {
        return groupDao;
    }
}
