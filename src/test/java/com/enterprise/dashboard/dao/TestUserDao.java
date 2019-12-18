package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.utils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * The type Test user dao.
 */
public class TestUserDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao userDao;
    private GenericDao roleDao;
    private User user;
    private Role role;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        userDao = new GenericDao(User.class);
        roleDao = new GenericDao(Role.class);
//        User
        user = new User();
        user.setFirstName("UserFirstName");
        user.setLastName("UserLastName");
        user.setUserName("testuser");
        user.setPassword("123");
        user.setDob(getSqlDate("2019-01-01", logger));
//        Role
        role = new Role();
        role.setName("testRole");
        role.setUserName(user.getUserName());
        role.setUser(user);
    }

    /**
     * Gets all users.
     */
    @Test
    public void getAllUsers() {
        assertEquals(4, userDao.getAll().size());
    }

    /**
     * Insert user.
     */
    @Test
    public void insertUser() {
        userDao.insert(user);
    }

    /**
     * Insert user with role.
     */
    @Test void insertUserWithRole() {
        int id = userDao.insert(user);
        roleDao.insert(role);

        User resultUser = (User) userDao.getById(id);
        assertTrue(resultUser.getRole() != null);
        assertEquals(resultUser.getRole().getName(), role.getName() );
    }

    /**
     * Delete user.
     */
    @Test void deleteUser() {
        userDao.insert(user);
        assertEquals(userDao.getAll().size(), 5);
        userDao.delete(user);
        assertEquals(userDao.getAll().size(), 4);
    }

    /**
     * Delete user with role.
     */
    @Test void deleteUserWithRole() {
        userDao.insert(user);
        roleDao.insert(role);
        assertEquals(userDao.getAll().size(), 5);
        assertEquals(roleDao.getAll().size(), 5);

        userDao.delete(user);
        assertEquals(userDao.getAll().size(), 4);
        assertEquals(roleDao.getAll().size(), 4);
    }

    /**
     * Update user.
     */
    @Test void updateUser() {
        user.setUserName("test1234");
        userDao.saveOrUpdate(user);
        List<User> users = userDao.getByProperty("userName", "test1234");
        assertEquals(user.getUserName(), users.get(0).getUserName());

    }
}
