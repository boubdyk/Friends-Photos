import com.bionic.friendsphotos.modelstructure.Groups;
import com.bionic.friendsphotos.modelstructure.TableGroup;
import com.bionic.friendsphotos.modelstructure.TableUser;

import java.util.ArrayList;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        Groups data = new Groups();
        ArrayList<TableUser> users = data.getUsersInGroup(7);

        for (TableUser user : users) {
            System.out.println("FB: "+user.getFbProfile()+ ",  ID: "+user.getIdDevice());
        }



        /*data.createGroup();
        for (TableGroup groups: data.selectAllFromTableGroups()) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }*/
    }
}
