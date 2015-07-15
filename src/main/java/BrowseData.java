import com.bionic.friendsphotos.entity.Group;
import com.bionic.friendsphotos.service.GroupService;

import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        GroupService groupService = new GroupService();
        List<Group> list = groupService.getAll();
        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %-40s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), groups.getCreatorId(), "\n");
        }

        System.out.println("\n\n\nFind by id = 5   " + groupService.findById(5));
        /*groupService.delete(16);
        list = groupService.getAll();
        for (Group groups: list) {
            System.out.format("%-5d %-25s %-3d %-20s %-40s %s", groups.getId(), groups.getName(),
                    groups.getType(), groups.getPassword(), groups.getCreatorId(), "\n");
        }*/
    }
}
