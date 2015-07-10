import com.bionic.friendsphotos.modelstructure.Groups;
import com.bionic.friendsphotos.entities.User;

import java.util.ArrayList;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        Groups data = new Groups();
        ArrayList<User> users = data.getUsersInGroup(7);

        for (User user : users) {
            System.out.println("FB: "+user.getFbProfile()+ ",  ID: "+user.getIdDevice());
        }

        data.saveGroup(3, "Holliday", "ppp");
        data.setPassword(3, "h111");


        /*data.createGroup();
        for (Group groups: data.getAllGroups()) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }*/
    }
}
