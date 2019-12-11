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
    GenericDao errorDao;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");

        userDao = new GenericDao(User.class);
        teamDao = new GenericDao(Team.class);
        applicationDao = new GenericDao(Application.class);
        errorDao = new GenericDao(ErrorData.class);
    }
    @Test
    public void getAllSuccess() {
        System.out.println(((Application) applicationDao.getById(1)).getErrorDataSet());
    }

    @Test
    public void getTeamApplication() {
        System.out.println(applicationDao.getByProperty("teamId", "1"));
    }
}
