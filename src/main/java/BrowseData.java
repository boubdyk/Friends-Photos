import com.bionic.friendsphotos.entity.Device;
import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.DevicesService;
import com.bionic.friendsphotos.service.GroupService;

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
                    groups.getType(), groups.getPassword(), groups.getIdCreator() + "\n");
        }
        System.out.println();
    }

    static void browseTableDevices() {
        System.out.println();
        for (Device devices: devicesService.getAll()) {
            System.out.format("%-30s %-20s %-20d %-20s %s", devices.getIdDevice(),
                    devices.getDescription(),
                    devices.getFbProfile(),
                    devices.getName(), "\n");
        }
    }

    public static void main(String[] args) {

        Group group = groupService.findById(9L);
//        group.setIdCreator("pppppp");
        System.out.println(group);
        Device dv = devicesService.findById("pppppp");
        Device dv1 = devicesService.findById("erer");
        Device dv2 = devicesService.findById("ioioioi");
        System.out.println(dv + "\n" + dv1 + "\n" + dv2);
        List<Device> list = group.getDevices();
        list.add(dv);
        list.add(dv1);
        list.add(dv2);
        group.setDevices(list);
        group.setIdCreator("ccc");
        groupService.updateGroup(group);

        System.out.println("All Devices : " + group);
        groupService.deleteDeviceFromGroup(40L, "erer");
        System.out.println("All Devices : " + group);


//        Device dv = devicesService.findById("ioioioi");
//        System.out.println(dv);
//        Group g1 = groupService.findById(10L);
//        Group g2 = groupService.findById(40L);
//        Group g3 = groupService.findById(6L);
//        System.out.println(g1 + "\n" + g2 + "\n" + g3);
//        Set<Group> list = dv.getGroups();
//        list.add(g1);
//        list.add(g2);
//        list.add(g3);
//
//        System.out.println(dv);
//        devicesService.updateDevice(dv);
//
//        devicesService.deleteGroupFromDevice("ioioioi", 6L);
//        System.out.println(dv);


        browseTableGroup();
        browseTableDevices();

        System.out.println();

//        Device d = devicesService.findById("pppppp");
//        Group g1 = groupService.findById(2L);
//        devicesService.addGroupToDevice(d.getIdDevice(), g1.getId());
//        System.out.println(d);
//
//        devicesService.deleteGroupFromDevice("pppppp", g1.getId());
//        System.out.println(d);
//
//        System.out.println("IOIOIOI" + devicesService.findById("ioioioi"));
//
//        Group g2 = groupService.findById(3L);
//        System.out.println(g2);
//        groupService.addDeviceToGroup(g2.getId(), d.getIdDevice());
//        System.out.println(g2);
//        groupService.deleteDeviceFromGroup(g2.getId(), d.getIdDevice());
//        System.out.println(g2);
    }
}
