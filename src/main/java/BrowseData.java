import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;
import com.bionic.friendsphotos.service.PhotoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.File;
import java.math.BigInteger;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.*;

/**
 * Created by Bogdan Sliptsov on 07.07.2015.
 */

public class BrowseData {
//    static GroupService groupService = new GroupService();
//    static DevicesService devicesService = new DevicesService();
//    static PhotoRestService photoService = new PhotoRestService();


    private static ApplicationContext context;
    private static GroupService groupService;
    private static DevicesService devicesService;
    private static PhotoService photoService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        groupService = context.getBean(GroupService.class);
        devicesService = context.getBean(DevicesService.class);
        photoService = context.getBean(PhotoService.class);
    }

    static void browseTableGroup() {
        System.out.println("\n");
        List<Group> list = groupService.getAllGroups();

        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %-20s %.7f %.7f %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), groups.getIdCreator(),
                            groups.getLatitude(),
                    groups.getLongitude(), "\n");
        }
        System.out.println();
    }

    static void browseTableDevices() {
        System.out.println();

        for (Device devices: devicesService.getAll()) {
            System.out.format("%-30s %-20s %-20d %-20s %-5d %s", devices.getIdDevice(),

                    devices.getDescription(),
                    devices.getFbProfile(),
                    devices.getName(),
                    devices.getCurrentGroup(), "\n");
        }
    }

    public static void main(String[] args) throws IOException {
//
//        File f = new File("src/main/resources/META-INF/app-context.xml");
//        System.out.println("Exist test: " + f.exists());

        /*
        browseTableGroup();
        browseTableDevices();


        System.out.println(devicesService.findById("bbb"));
        System.out.println(groupService.findById(40L));

        //System.out.println(devicesService.removeMembersFromCurrentGroup("bbb", "erer"));

        System.out.println(devicesService.findById("bbb"));
        System.out.println(groupService.findById(40L));
        System.out.println(devicesService.findById("erer"));



        Photo photo = new Photo(3L , "aaa", "photo.2015.(mysuperphoto).png");

        photoService.saveSinglePhoto(photo, new FileInputStream("D:\\qwe.png"));

        System.out.println(photoService.getPhoto(photo).toString());*/


        // Get all group photos
        List<Photo> list = photoService.getGroupInfo(groupService.findById(10L));

        // Get photo by photo ID
        //Photo photo = photoService.getSingleInfo(list.get(0).getId());

        // Download this photo
        //photoService.getPhotoFromFileSystem(photo);
        System.out.println(list.get(0).toString());

        String photo = photoService.getSingleFile(list.get(0));

        //list.get(0).setName("base64test.png");

        photoService.saveSinglePhoto(new Photo(10L, "aaa", "base64testik.png"), photo);


    }
}
