package peristance;

import java.util.ArrayList;
import java.util.List;

import business.User;

/**
 * Created by senpay on 15.2.18.
 */
public class InMemoryUserRepository implements IUserRepository {

    private List<User> userList = new ArrayList<>();

    @Override
    public synchronized void saveUser(User userToSave) {
        userList.add(userToSave);
    }

    @Override
    public synchronized List<User> getUsers() {
        return userList;
    }
}
