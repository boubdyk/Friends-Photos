/**
 * Created by c267 on 08.07.2015.
 */
public class Group {
    private int id;
    private String name;
    private byte type;
    private String pass;
    private String creatorId;

    public Group(int id, String name, byte type, String pass, String creatorId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pass = pass;
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getType() {
        return type;
    }

    public String getPass() {
        return pass;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
