package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Photo;

import javax.persistence.EntityManager;
import java.io.*;

/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoDao implements GenericDao<Photo, Long> {

    private EntityManager em = EMFactory.getInstance();

    public PhotoDao() {
    }


    public void savePhoto(Photo photo, InputStream uploadedInputStream) throws IOException {

/*        String dir = "D:\\FriendsPhotosBase\\"
                + photo.getGroupId() + "\\";*/

        File dir = new File("D:\\FriendsPhotosBase\\"
                + photo.getGroupId() + "\\");
        dir.mkdirs();
        OutputStream out = new FileOutputStream(dir + "\\" + photo.getName());

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();

//        create(photo);
    }

    @Override
    public Long create(Photo newInstance) {

        em.getTransaction().begin();
        em.persist(newInstance);
        em.getTransaction().commit();
        return newInstance.getId();
    }

    @Override
    public Photo read(Long id) {
        return null;
    }

    @Override
    public Photo update(Photo transientObject) {
        return null;
    }

    @Override
    public void delete(Long persistentObject) {

    }
}
