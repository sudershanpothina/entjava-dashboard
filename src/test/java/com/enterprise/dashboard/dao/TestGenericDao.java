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
//
//public class TestGenericDao {
//    GenericDao errorDao;
//    GenericDao applicationDao;
//
//    @BeforeEach
//    public void setup() {
//        Database database = Database.getInstance();
//        database.runSQL("cleanupdb.sql");
//        errorDao = new GenericDao(ErrorData.class);
//        applicationDao = new GenericDao(Application.class);
//    }
//    @Test
//    public void getAllSuccess() {
//        assertEquals(errorDao.getAll().size(), 3);
//    }
//    @Test
//    public void getByIdSuccess() {
//        ErrorData errorData = (ErrorData) errorDao.getById(1);
//        assertEquals((int) errorData.getId(), 1);
//    }
//    @Test
//    public void deleleSuccess() {
//        ErrorData errorData = (ErrorData) errorDao.getById(1);
//        errorDao.delete(errorData);
//        assertEquals(errorDao.getAll().size(), 2);
//    }
//    @Test
//    public void updateSuccess() {
//        ErrorData errorData = (ErrorData) errorDao.getById(1);
//        errorData.setMessage("ERR-111");
//        errorDao.saveOrUpdate(errorData);
//        ErrorData errorDataUpdated = (ErrorData) errorDao.getById(1);
//        assertEquals(errorDataUpdated.getMessage(), "ERR-111");
//    }
//    @Test
//    public void insertSuccess() {
//        Application application = (Application) applicationDao.getById(1);
//        ErrorData errorData = new ErrorData("ERR-111", "this is a test error ", application);
//        int id = errorDao.insert(errorData);
//        assertEquals(errorData, errorDao.getById(id));
//    }
//}
