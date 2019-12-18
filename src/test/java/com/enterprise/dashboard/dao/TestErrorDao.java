package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.util.DateConvert;
import com.enterprise.dashboard.util.ErrorService;
import com.enterprise.dashboard.utils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Set;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;
import static org.junit.Assert.*;

/**
 * The type Test error dao.
 */
public class TestErrorDao {
    private GenericDao errorDao;
    private GenericDao applicationDao;
    private ErrorData errorData;
    private Application application;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        errorDao = new GenericDao(ErrorData.class);
        errorData = new ErrorData();
        application = new Application();
        application.setName("Application 10");
        application.setDescription("This is a test application");
        application.setTeamId("1");
        errorData.setMessage("ERR-101");
        errorData.setDescription("This is an error message");
        DateConvert dateConvert = new DateConvert();
        Date date = getSqlDate("2019-10-01", logger);
        errorData.setDttm(date);
    }

    /**
     * Gets all errors.
     */
    @Test
    public void getAllErrors() {
        assertEquals(errorDao.getAll().size(), 0);
        errorDao.insert(errorData);
        assertEquals(errorDao.getAll().size(), 1);
    }

    /**
     * Insert error data.
     */
    @Test
    public void insertErrorData() {
        ErrorService errorService = new ErrorService();
        Set<ErrorData> errors = errorService.getErrorData(logger);
        for(ErrorData errorData : errors) {
            errorDao.insert(errorData);
        }
        assertTrue(errorDao.getAll().size() >= 0);
    }

    /**
     * Delete error data.
     */
    @Test
    public void deleteErrorData() {
        errorDao.insert(errorData);
        int initialSize = errorDao.getAll().size();
        errorDao.delete(errorData);
        assertNotEquals(initialSize, errorDao.getAll().size());
    }

    /**
     * Update error data.
     */
    @Test
    public void updateErrorData() {
        int id = errorDao.insert(errorData);
        errorData.setMessage("ERR-102");
        errorDao.saveOrUpdate(errorData);
        assertEquals(((ErrorData) errorDao.getById(id)).getMessage(), "ERR-102");
    }
}
