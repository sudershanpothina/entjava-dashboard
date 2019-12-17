package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.utils.Database;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Theories.class)
public class TestTheories {

    public static GenericDao userDao;
    public static GenericDao roleDao;
    public static String test ="1";

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");

        userDao = new GenericDao(User.class);
    }
    @DataPoint("test123")
    public static String name = "test";


    @Theory
    public void testTheory(@FromDataPoints("test123") String name) {
        System.out.println(name);
        userDao = new GenericDao(User.class);
        userDao.getById(1);
        System.out.println(test);
    }

    @Test
    public void test123() {
        System.out.println("sadf");
        userDao = new GenericDao(User.class);

        System.out.println(((User)userDao.getById(1)).getRole());
    }

    @Test
    public void addRoletoUser() {
        userDao = new GenericDao(User.class);
        roleDao = new GenericDao(Role.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        Date utilDate = new Date();
        sqlDate = new java.sql.Date(utilDate.getTime());
        User user = new User("test1","test2", "testUser1","2",  sqlDate, "1");
        System.out.println(user);
        Role role = new Role();
        role.setName("testRole");
        role.setUserName(user.getUserName());
//        user.setRole(role);
        userDao.insert(user);
        role.setUser(user);
        roleDao.insert(role);
        System.out.println(role);
        userDao.delete(user);

    }
}
