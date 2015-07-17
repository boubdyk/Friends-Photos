import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.GroupService;

import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    static GroupService groupService;

    static void browseTableGroup() {
        System.out.println("\n");
        List<Group> list = groupService.getAllGroups();
        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %-40s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), groups.getCreatorId(), "\n");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        groupService = new GroupService();

        //browse data
        browseTableGroup();

        //checking method findById()
        System.out.println("\n\n\nFind by id = 5   " + groupService.findById(600L));

        //checking method create()
        /*Group obj = new Group(null, "Tra-ta-ta", new Byte(String.valueOf(1)), "olo-lo", "boooooooooooo");
        System.out.println(groupService.createGroup(obj));
        browseTableGroup();*/

        //checking method update()
        Group obj1 = new Group(null, null, null, null);
        groupService.updateGroup(obj1);
        browseTableGroup();

        //checking method delete()
        System.out.println(groupService.deleteGroup(32L));
        browseTableGroup();

        //checking method getNameById()
        System.out.println(groupService.getNameById(30L));
        System.out.println(groupService.getNameById(300L));

        Group obj = new Group();
        System.out.println(groupService.createGroup(obj));
        browseTableGroup();
    }
}
