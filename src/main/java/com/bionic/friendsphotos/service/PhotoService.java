package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.PhotoDao;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import java.io.*;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.*;
/**
 * Service to handling photos
 * @author Yevhenii Semenov
 *
 */

@Named
@Transactional
public class PhotoService {


    private static final String DIRECTORY = "E:\\FriendsPhotosBase\\";
    @Inject
    private PhotoDao photoDao;


    public PhotoService() {

//        photoDao = new PhotoDao();
    }

    /**
     * Save photo to filesystem and entity into DataBase
     *
     * @param photo Photo entity
     * @param base64 Photo-file encoded to Base64 String
     *
    * */
    public void saveSinglePhoto(Photo photo, String base64) {
        photoDao.create(saveToFileSystemBase64(photo, base64));
        //photoDao.create(saveToFileSystem(photo, uploadedInputStream));
    }


    /**
     * Pulls out a photo from the file system and encoded it to Base64 String
     *
     * @param photo Photo entity
     * @return Encoded to Base64 photo
    * */
    public String getSingleFile(Photo photo) {
        byte[] bytes = null;
        try {
            InputStream stream = new FileInputStream(DIRECTORY + photo.getGroupId() + "\\" + photo.getName());
            int read = 0;
            bytes = new byte[stream.available()];
            read = stream.read(bytes);

            if (read <= 0) throw new IOException();
        } catch (FileNotFoundException e) {
            System.out.println("Photo Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O Error");
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * @param id photo ID
     * @return Photo entity from database
     */
    public Photo getSingleInfo(Long id) {
        return photoDao.read(id);
    }

    /**
     * The method allows to find out which files belong to the group
     *
     * @param group Group entity
     * @return List that contains the entities with all the photos in this group
     */

    public List<Photo> getGroupInfo(Group group) {
        if (group.getId() == null) return null;
        return photoDao.getPhotosByGroup(group);
    }

    /* Private methods */

    /**
     * 1.Check if directory for this group is exist and create if necessary
     * 2.Check if names duplicated and change if it necessary
     * 3.Decode Base64 String to bytes and save into file system
     *
     * @param photo Photo entity
     * @param base64 Image encoded to Base64
     * @return Photo entity
     */
    private Photo saveToFileSystemBase64(Photo photo, String base64) {

        File dir = new File(DIRECTORY + photo.getGroupId() + "\\");
        if (!dir.exists()) { dir.mkdirs(); }
        File file = new File(dir, photo.getName());

        if (file.exists()) {
            int counter = 1;
            String newName = photo.getName();
            do {
                newName = changeName(newName, counter++);
                file = new File(DIRECTORY + photo.getGroupId() + "\\" + newName);
            } while (file.exists());
            photo.setName(newName);
        }

        try (OutputStream out = new FileOutputStream(file)) {
            int read = 0;
            byte[] bytes = Base64.getDecoder().decode(base64);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error I/O");
            e.printStackTrace();
        }

        return photo;
    }

    /**
     * Add counter to the beginning of the name
     *
     * @param name
     * @param counter
     * @return new name
     */
    private String changeName(String name, int counter) {
        String newPhotoName;
        if(name.startsWith("(")) {
            String [] separatedName = name.split("\\)", 2);
            newPhotoName = "(" + (counter++) + ")";
            for (int i = 1; i < separatedName.length ; i++) {
                newPhotoName += separatedName[i];
            }
        } else {
            newPhotoName = "(" + (counter++) + ") " + name;
        }
        return newPhotoName;
    }


}
