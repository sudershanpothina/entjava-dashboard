//package com.enterprise.dashboard.dao;
//
//import com.enterprise.dashboard.model.Application;
//import com.enterprise.dashboard.model.ErrorData;
//import com.enterprise.dashboard.utils.Database;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestErrorDao {
//
//    ErrorDataDao errorDataDao;
//    ApplicationDao applicationDao;
//
//    @BeforeEach
//    public void setup() {
//        Database database = Database.getInstance();
//        database.runSQL("cleanupdb.sql");
//        errorDataDao = new ErrorDataDao();
//        applicationDao = new ApplicationDao();
//    }
//
//    @Test
//    public void getAllSuccess() {
//        assertEquals(errorDataDao.getAll().size(), 3);
//    }
//    @Test
//    public void getByIdSuccess() {
//        ErrorData errorData = errorDataDao.getById(1);
//        assertEquals((int) errorData.getId(), 1);
//    }
//    @Test
//    public void deleleSuccess() {
//        ErrorData errorData = errorDataDao.getById(1);
//        errorDataDao.delete(errorData);
//        assertEquals(errorDataDao.getAll().size(), 2);
//    }
//    @Test
//    public void updateSuccess() {
//        ErrorData errorData = errorDataDao.getById(1);
//        errorData.setMessage("ERR-111");
//        errorDataDao.saveOrUpdate(errorData);
//        ErrorData errorDataUpdated = errorDataDao.getById(1);
//        assertEquals(errorDataUpdated.getMessage(), "ERR-111");
//    }
//    @Test
//    public void insertSuccess() {
//        Application application = applicationDao.getById(1);
//        ErrorData errorData = new ErrorData("ERR-111", "this is a test error ", application);
//        int id = errorDataDao.insert(errorData);
//        assertEquals(errorData, errorDataDao.getById(id));
//    }
//}
