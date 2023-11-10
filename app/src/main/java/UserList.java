import java.util.ArrayList;

public class UserList extends List {
    private ArrayList<UserProfile> users = new ArrayList<UserProfile>();

    UserList() {
        this.load();
    }

    public void load() {
        // TODO: implement
        users.add(new UserProfile(1, "dave"));
        users.add(new UserProfile(2, "steve"));
    }

    public UserProfile getByName(String name) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                return users.get(i);
            }
        }
        // TODO: throw not found exception
        return null; // to make android studio not mad at me
    }

    public UserProfile getById(int id) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId() == id) {
                return users.get(i);
            }
        }
        // TODO: throw not found exception
        return null; // to make android studio not mad at me
    }
}
