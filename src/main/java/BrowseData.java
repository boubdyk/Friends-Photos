import com.bionic.friendsphotos.modelstructure.Groups;
import com.bionic.friendsphotos.modelstructure.TableGroup;

/**
 * Created by c265 on 07.07.2015.
 */

public class BrowseData {
    public static void main(String[] args) {
        Groups data = new Groups();
        data.createGroup();
        for (TableGroup groups: data.selectAllFromTableGroups()) {
            System.out.println(groups.getId() + " " + groups.getName() + " " + groups.getType() + groups.getPassword() + " " + groups.getCreatorId());
        }
    }
}
