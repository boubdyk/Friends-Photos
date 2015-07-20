import com.bionic.friendsphotos.entity.Devices;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    static GroupService groupService = new GroupService();
    static DevicesService devicesService = new DevicesService();

    static void browseTableGroup() {
        System.out.println("\n");
        List<Group> list = groupService.getAllGroups();
        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), "\n");
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

        Group group = groupService.findById(40L);
        System.out.println(group);
        Devices dv = devicesService.findById("pppppp");
        Devices dv1 = devicesService.findById("erer");
        Devices dv2 = devicesService.findById("ioioioi");
        System.out.println(dv + "\n" + dv1 + "\n" + dv2);
        Set<Devices> list = group.getDevices();
        list.add(dv);
        list.add(dv1);
        list.add(dv2);

        group.setDevices(list);
//        groupService.createGroup(group);
        groupService.updateGroup(group);


        System.out.println("All Devices : " + group);
        groupService.deleteDeviceFromGroup(40L, "erer");
        System.out.println("All Devices : " + group);


//        Devices dv = devicesService.findById("ioioioi");
//        System.out.println(dv);
//        Group g1 = groupService.findById(10L);
//        Group g2 = groupService.findById(40L);
//        Group g3 = groupService.findById(6L);
//        System.out.println(g1 + "\n" + g2 + "\n" + g3);
//        Set<Group> list = dv.getGroups();
//        list.add(g1);
//        list.add(g2);
//        list.add(g3);
//        dv.setGroups(list);
//        System.out.println(dv);
//        devicesService.updateDevice(dv);
//
//        devicesService.deleteGroupFromDevice("ioioioi", 6L);
//        System.out.println(dv);


        browseTableGroup();
        browseTableDevices();

        System.out.println();
    }
}
