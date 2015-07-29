package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoDao implements GenericDao<Photo, Long> {

    private EntityManager em = EMFactory.getInstance();

    public PhotoDao() {
    }


    @Override
    public Long create(Photo newInstance) {
        em.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public Photo read(Long id) {
        return em.find(Photo.class, id);
    }

    @Override
    public Photo update(Photo transientObject) {
        return null;
    }

    @Override
    public void delete(Long persistentObject) {
    }

    public List<Photo> getPhotosByGroup(Group group) {
        Query query = em.createQuery("from Photo where groupId = :g_id ");
        query.setParameter("g_id", group.getId());
        List<Photo> photos = query.getResultList();
        return photos;
    }
}
