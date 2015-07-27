package com.bionic.friendsphotos.service;

import com.bionic.friendsphotos.dao.PhotoDao;
import com.bionic.friendsphotos.entity.Photo;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import org.w3c.dom.*;
/**
 * Created by c267 on 21.07.2015.
 */
public class PhotoService {
    private static PhotoDao photoDao;

    public PhotoService() {
        photoDao = new PhotoDao();
    }

    public void create(Photo photo){
        photoDao.create(photo);
    }

    public void savePhoto(Photo photo, InputStream uploadedInputStream) {


        try {
            photo = photoDao.savePhoto(photo, uploadedInputStream);
        } catch (IOException e) {
            e.printStackTrace();

        }

        photoDao.create(photo);
    }

    public File getPhoto (Photo photo) {
        return photoDao.getPhoto(photo);
    }

}
