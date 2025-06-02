package org.Kaminski.userservice.dao;

import org.Kaminski.userservice.entity.User;
import org.Kaminski.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOImplTest {

    private UserDAO userDAO;

    @BeforeAll
    void init() {
        System.setProperty("hibernate.config", "hibernate.test.cfg.xml");
        userDAO = new UserDAOImpl();
    }

    @BeforeEach
    void clearTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            tx.commit();
        }
    }

    @Test
    void testCreateAndGetUser() {
        User user = new User("Kirill", "Kirill.ex@mail.ru", 25);
        userDAO.createUser(user);

        List<User> allUsers = userDAO.getAllUsers();
        assertEquals(1, allUsers.size());

        User fetched = userDAO.getUser(allUsers.get(0).getId());
        assertEquals("Kirill", fetched.getName());
        assertEquals("Kirill.ex@mail.ru", fetched.getEmail());
    }

    @Test
    void testUpdateUser() {
        User user = new User("Bob", "Bob.txt@mail.ru", 30);
        userDAO.createUser(user);

        User fetched = userDAO.getAllUsers().get(0);
        fetched.setAge(35);
        userDAO.updateUser(fetched);

        User updated = userDAO.getUser(fetched.getId());
        assertEquals(35, updated.getAge());
    }

    @Test
    void testDeleteUser() {
        User user = new User("John", "John.pop@mail.ru", 40);
        userDAO.createUser(user);

        Long userId = userDAO.getAllUsers().get(0).getId();
        userDAO.deleteUser(userId);

        assertNull(userDAO.getUser(userId));
        assertTrue(userDAO.getAllUsers().isEmpty());
    }
}
