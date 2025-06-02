package org.Kaminski.userservice.service;

import org.Kaminski.userservice.dao.UserDAO;
import org.Kaminski.userservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserDAO userDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    void testCreateUser() {
        User user = new User("Kirill", "Kirill.ex@mail.ru", 31);
        userService.createUser(user);
        verify(userDAO, times(1)).createUser(user);
    }

    @Test
    void testGetUser() {
        User user = new User("Bob", "Bob.txt@mail.ru", 15);
        when(userDAO.getUser(1L)).thenReturn(user);

        User result = userService.getUser(1L);
        assertEquals("Bob", result.getName());
        verify(userDAO).getUser(1L);
    }

    @Test
    void testGetAllUsers() {
        when(userDAO.getAllUsers()).thenReturn(List.of(new User("John", "John.pop@mail.ru", 13)));
        assertEquals(1, userService.getAllUsers().size());
        verify(userDAO).getAllUsers();
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userDAO).deleteUser(1L);
    }

    @Test
    void testUpdateUser() {
        User user = new User("Jane", "Jane.hex@mail.ru", 26);
        user.setId(2L);
        userService.updateUser(user);
        verify(userDAO).updateUser(user);
    }
}
