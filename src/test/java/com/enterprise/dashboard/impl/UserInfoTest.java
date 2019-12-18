package com.enterprise.dashboard.impl;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.utils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;
import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {
    private GenericDao userDao;
    private GenericDao roleDao;
    private UserInfo userInfo;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Date date;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        userInfo = new UserInfo();
        userDao = new GenericDao(User.class);
        roleDao = new GenericDao(Role.class);
        date = getSqlDate("2019-05-01", logger);
    }

    @Test
    void updateUser() {
        userInfo.updateUser("1","Tom","Ramo", "tramo", "123", date, "http://url", "test-role", userDao, roleDao);
        assertEquals(((User)userDao.getById(1)).getFirstName(), "Tom");
    }

    @Test
    void createUser() {
        userInfo.createUser("Tom", "Ramo", "tramo",
                "23", date, "1", "team-memb", userDao, roleDao);
        assertEquals(userDao.getAll().size(), 5);
        assertEquals(roleDao.getAll().size(), 5);
    }
}