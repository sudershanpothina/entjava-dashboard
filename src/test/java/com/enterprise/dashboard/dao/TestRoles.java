package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.utils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRoles {
    private GenericDao roleDao;
    private GenericDao userDao;
    private Role role;
    private User user;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        roleDao = new GenericDao(Role.class);
        userDao = new GenericDao(User.class);
//        Role
        role = new Role();
        role.setName("testRole");
        user = (User) userDao.getById(1);
        role.setUserName(user.getUserName());
        role.setUser(user);
    }

    @Test
    public void getAllRoles() {
        assertEquals(4, roleDao.getAll().size());
    }

    @Test
    public void insertRole() {
        int id = roleDao.insert(role);
        assertTrue(id != 0);
    }

    @Test
    public void updateRole() {
        int id = roleDao.insert(role);
        role.setName("team-member");
        roleDao.saveOrUpdate(role);
        Role role1 = (Role) roleDao.getById(id);
        assertEquals(role1.getName(), "team-member");

    }

    @Test void deleteRole() {
        roleDao.delete(roleDao.getById(1));
        assertTrue(roleDao.getAll().size() != 4);
    }
}
