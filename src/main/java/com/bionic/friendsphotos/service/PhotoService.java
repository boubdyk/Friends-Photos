package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.PhotoDao;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import java.io.*;
import java.util.Base64;
import java.util.List;

/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoService {
    private PhotoDao photoDao;
    private static final String DIRECTORY = "D:\\FriendsPhotosBase\\";

    public PhotoService() {
        photoDao = new PhotoDao();
    }

    public void saveSinglePhoto(Photo photo, String base64) {
        photoDao.create(saveToFileSystemBase64(photo, base64));
        //photoDao.create(saveToFileSystem(photo, uploadedInputStream));
    }

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

    public Photo getSingleInfo(Long id) {
        return photoDao.read(id);
    }

    public List<Photo> getGroupInfo(Group group) {
        return photoDao.getPhotosByGroup(group);
    }

    /* Private methods */

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
        return name;
    }


}
