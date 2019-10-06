package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.utils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestApplicationDao {
    ErrorDataDao errorDataDao;
    ApplicationDao applicationDao;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        errorDataDao = new ErrorDataDao();
        applicationDao = new ApplicationDao();
    }
    @Test
    public void getAllSuccess() {
        assertEquals(applicationDao.getAll().size(), 2);
    }
    @Test
    public void getByIdSuccess() {
        Application application = applicationDao.getById(1);
        assertEquals((int) application.getId(), 1);
    }
    @Test
    public void deleteSuccess() {
        Application application = applicationDao.getById(1);
        applicationDao.delete(application);
        assertEquals(applicationDao.getAll().size(), 1);
    }
    @Test
    public void updateApplicationSuccess() {
        Application application = applicationDao.getById(1);
        application.setDescription("This is Application 1 for test");
        applicationDao.updateOrSave(application);
        assertEquals(applicationDao.getById(1), application);
    }
}
