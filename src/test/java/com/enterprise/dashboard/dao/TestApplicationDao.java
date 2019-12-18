package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.utils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestApplicationDao {
    GenericDao errorDataDao;
    GenericDao applicationDao;

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        errorDataDao = new GenericDao(ErrorData.class);
        applicationDao = new GenericDao(Application.class);
    }
    @Test
    public void getAllSuccess() {
        assertEquals(applicationDao.getAll().size(), 1);
    }
    @Test
    public void getByIdSuccess() {
        Application application = (Application) applicationDao.getById(1);
        assertEquals((int) application.getId(), 1);
    }
    @Test
    public void deleteSuccess() {
        Application application = (Application) applicationDao.getById(1);
        applicationDao.delete(application);
        assertEquals(applicationDao.getAll().size(), 0);
    }
    @Test
    public void updateApplicationSuccess() {
        Application application = (Application) applicationDao.getById(1);
        application.setDescription("This is Application 1 for test");
        applicationDao.saveOrUpdate(application);
        assertEquals(applicationDao.getById(1), application);
    }
    @Test
    public void insertSuccess() {
        Application application = new Application();
        application.setName("Application 2");
        application.setDescription("This is test description");
        int id = applicationDao.insert(application);
        assertEquals(applicationDao.getAll().size(), 2);
    }
    @Test
    public void insertWithErrorRecordSuccess() {
        Application application = new Application();
        application.setName("Application 2");
        application.setDescription("This is test description");
        ErrorData errorData = new ErrorData("ERR-111", "this is a test error ", application);
        Set<ErrorData> errorDataList = new HashSet<>();
        errorDataList.add(errorData);
        application.setErrorDataSet(errorDataList);
        applicationDao.insert(application);
    }
}
