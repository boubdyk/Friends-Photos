package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by c267 on 21.07.2015.
 */

@Repository
public class PhotoDao implements GenericDao<Photo, Long> {

    private static final String DIRECTORY = "D:\\FriendsPhotosBase\\";

    @PersistenceContext
    private EntityManager entityManager; /* = EMFactory.getInstance();*/


    public PhotoDao() {
    }


    @Override
    public Long create(Photo newInstance) {

        entityManager.persist(newInstance);

        return newInstance.getId();
    }

    @Override
    public Photo read(Long id) {
        return entityManager.find(Photo.class, id);
    }

    @Override
    public Photo update(Photo transientObject) {
        return null;
    }

    @Override
    public void delete(Long persistentObject) {
    }

    public List<Photo> getPhotosByGroup(Group group) {
        Query query = entityManager.createQuery("from Photo where groupId = :g_id ");
        query.setParameter("g_id", group.getId());
        List<Photo> photos = query.getResultList();
        return photos;
    }
}
