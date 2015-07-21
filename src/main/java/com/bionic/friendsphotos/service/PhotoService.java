package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.PhotoDao;
import com.bionic.friendsphotos.entity.Photo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoService {
    private static PhotoDao photoDao;

    public PhotoService() {
        photoDao = new PhotoDao();
    }

    public void create(Photo photo, InputStream uploadedInputStream){

        try {
            photoDao.savePhoto(photo, uploadedInputStream);
        } catch (IOException e) {
            System.out.println("Pzdc voobshe :(");
            e.printStackTrace();
        }
    }
}
