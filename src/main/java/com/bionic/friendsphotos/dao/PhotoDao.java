package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.Photo;

import javax.persistence.EntityManager;
import java.io.*;
import java.nio.file.*;

/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoDao implements GenericDao<Photo, Long> {

    private static final String DIRECTORY = "E:\\FriendsPhotosBase\\";

    private EntityManager em = EMFactory.getInstance();

    public PhotoDao() {
    }


    public Photo savePhoto(Photo photo, InputStream uploadedInputStream) throws IOException {
        int counter = 1;

        File dir = new File(DIRECTORY + photo.getGroupId() + "\\");
        if (!dir.exists()) { dir.mkdirs(); }
        File file = new File(dir, photo.getName());
        while (file.exists()){
            String newPhotoName;

            String [] separatedName = photo.getName().split("\\.");
            if (separatedName[0].contains("(")) {
                String [] separatedNameWithCounter = separatedName[0].split("\\(");
                newPhotoName = separatedNameWithCounter[0] + "(" + (counter++) + ")." + separatedName[1];
            } else {
                separatedName[0] += "(" + (counter++) + ")";
                newPhotoName = separatedName[0] + "." + separatedName[1];
            }
            photo.setName(newPhotoName);

            file = new File(DIRECTORY + photo.getGroupId() + "\\" + newPhotoName);
        }

        OutputStream out = new FileOutputStream(file);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        // todo: if ecxeption ?
        out.flush();
        out.close();

        return photo;
    }

    public OutputStream getPhoto(Photo photo) throws IOException{
        return new FileOutputStream(DIRECTORY + photo.getGroupId() + "\\" + photo.getName());
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
