package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.model.Team;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.utils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBasic {
    GenericDao userDao;
    GenericDao teamDao;
    GenericDao applicationDao;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");

        userDao = new GenericDao(User.class);
        teamDao = new GenericDao(Team.class);
        applicationDao = new GenericDao(Application.class);
    }
    @Test
    public void getAllSuccess() {
        System.out.println(userDao.getAll());
        System.out.println(teamDao.getAll());
        System.out.println(applicationDao.getByProperty("teamId", "1"));
        Application application = new Application("Application3", "This is the third application", "1");
        applicationDao.insert(application);
        applicationDao.delete(application);
    }
}
