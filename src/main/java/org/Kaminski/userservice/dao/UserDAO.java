package org.Kaminski.userservice.dao;

import org.Kaminski.userservice.entity.User;
import java.util.List;

public interface UserDAO {
    User getUser(Long id);
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}

