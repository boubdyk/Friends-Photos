import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.entity.Photo;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;
import com.bionic.friendsphotos.service.PhotoService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    static GroupService groupService = new GroupService();
    static DevicesService devicesService = new DevicesService();
    static PhotoService photoService = new PhotoService();

    static void browseTableGroup() {
        System.out.println("\n");
        List<Group> list = groupService.getAllGroups();
        for (Group groups : list) {
            System.out.format("%-5d %-25s %-3d %-20s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), "\n");
        }
        System.out.println();
    }

    static void browseTableDevices() {
        System.out.println();
        for (Device devices : devicesService.getAll()) {
            System.out.format("%-30s %-20s %-20d %-20s %s", devices.getIdDevice(),
                    devices.getDescription(),
                    devices.getFbProfile(),
                    devices.getName(), "\n");
        }
    }

    public static void main(String[] args) throws IOException {

        Photo photo = new Photo(9L , "aaa", "photo.png");

        //photoService.savePhoto(photo, new FileInputStream("E:\\test.png"));
        //photoService.create(photo);

        System.out.println(photoService.getPhoto(photo).toString());

    }
}
