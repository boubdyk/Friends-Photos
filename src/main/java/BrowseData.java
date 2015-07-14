import com.bionic.friendsphotos.entitie.Group;
import com.bionic.friendsphotos.service.GroupService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        /*Groups data = new Groups();
        ArrayList<User> users = data.getUsersInGroup(7);*/

       /* for (User user : users) {
            System.out.println("FB: "+user.getFbProfile()+ ",  ID: "+user.getIdDevice());
        }

        data.saveGroup(3, "Holliday", "ppp");
        data.setPassword(3, "h111");
*/

       /* data.createGroup();
        for (Group groups: data.getAllGroups()) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }*/

        GroupService groupService = new GroupService();
        List<Group> list = groupService.findAll();
        for (Group groups: list) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }
    }
}
