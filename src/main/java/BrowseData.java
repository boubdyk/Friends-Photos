import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;
import com.bionic.friendsphotos.service.PhotoService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import java.util.*;

/**
 * Created by Bogdan Sliptsov on 07.07.2015.
 */

public class BrowseData {
    static GroupService groupService = new GroupService();
    static DevicesService devicesService = new DevicesService();
    static PhotoService photoService = new PhotoService();

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



        browseTableGroup();
        browseTableDevices();


        System.out.println(devicesService.findById("bbb"));
        System.out.println(groupService.findById(40L));

        //System.out.println(devicesService.removeMembersFromCurrentGroup("bbb", "erer"));

        System.out.println(devicesService.findById("bbb"));
        System.out.println(groupService.findById(40L));
        System.out.println(devicesService.findById("erer"));



       /* Photo photo = new Photo(3L , "aaa", "photo.2015.(mysuperphoto).png");

        photoService.savePhoto(photo, new FileInputStream("D:\\qwe.png"));

        System.out.println(photoService.getPhoto(photo).toString());*/

    }
}
