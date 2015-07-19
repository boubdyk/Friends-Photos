import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    static GroupService groupService;
    static DevicesService devicesService;

    static void browseTableGroup() {
        System.out.println("\n");
        List<Group> list = groupService.getAllGroups();
        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %-40s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), groups.getCreatorId(), "\n");
        }
        System.out.println();
    }

    static void browseTableDevices() {
        System.out.println();
        for (Devices devices: devicesService.getAllDevices()) {
            System.out.format("%-30s %-20s %-20d %-20s %s", devices.getIdDevice(),
                    devices.getDescription(),
                    devices.getFbProfile(),
                    devices.getName(), "\n");
        }
    }

    public static void main(String[] args) {
        groupService = new GroupService();
        devicesService = new DevicesService();

        Devices dv = new Devices("bbb", new BigInteger(String.valueOf(45454)), "Android");
//
//        ArrayList<Devices> list = new ArrayList<Devices>();
//        list.add(dv);

//        Group gr = new Group("DFGDFG", new Byte(String.valueOf(1)), "dfdffffff", list);
//        groupService.createGroup(gr);

        //browse data
//        devicesService.addNewDevice(dv);
        browseTableGroup();
        browseTableDevices();

        System.out.println(groupService.getIdOfAllDevicesInCurrentGroup(10L));
        System.out.println(devicesService.getIdOfAllDevicesInCurrentGroup("aaa"));
//        System.out.println(devicesService.getIdOfAllDevicesInCurrentGroup("bbb"));

    }
}
