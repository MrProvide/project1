package javau9.ca.db.abc.finalprojectalternativehf.Service;

import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;

import java.util.List;


public interface SimpleUserService {

    SimpleUser addUser(SimpleUser simpleUser);

    List<SimpleUser> getAllUsers();

    SimpleUser getUserById(Long id);

    SimpleUser updateUser(Long id, SimpleUser updatedUser);

    boolean deleteUser(Long id);
}



