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

//        Devices dv = new Devices("pppppp", new BigInteger(String.valueOf(45454)), "Android");
//        Devices dv1 = new Devices("erer", new BigInteger(String.valueOf(45454)), "Android");
//        Devices dv2 = new Devices("ioioioi", new BigInteger(String.valueOf(45454)), "Android");

//        devicesService.addNewDevice(dv);

//        ArrayList<Devices> list = new ArrayList<Devices>();
//        list.add(dv);

//        Group group = new Group("RTRTRT", new Byte(String.valueOf(1)), "dfdffffff");

//        group.setDevices(Arrays.asList(dv, dv1, dv2));
//        groupService.createGroup(group);

//        System.out.println("All Devices : " + groupService.getAllDevices(38L));


        Devices dv = devicesService.findById("ioioioi");
        System.out.println(dv);
        Group g1 = groupService.findById(40L);
        Group g2 = groupService.findById(41L);
        Group g3 = groupService.findById(39L);
        System.out.println(g1 + "\n" + g2 + "\n" + g3);
        dv.setGroups(Arrays.asList(g1, g2, g3));
        System.out.println(dv);
        devicesService.updateDevice(dv);


        browseTableGroup();
        browseTableDevices();

        System.out.println();
    }
}
