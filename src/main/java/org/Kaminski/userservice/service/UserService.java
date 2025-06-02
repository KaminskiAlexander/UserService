package org.Kaminski.userservice.service;

import org.Kaminski.userservice.dao.UserDAO;
import org.Kaminski.userservice.entity.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}