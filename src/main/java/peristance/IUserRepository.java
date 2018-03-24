package peristance;

import java.util.List;

import business.User;

/**
 * Created by senpay on 15.2.18.
 */
public interface IUserRepository {

    void saveUser(User userToSave);
    List<User> getUsers();

}
