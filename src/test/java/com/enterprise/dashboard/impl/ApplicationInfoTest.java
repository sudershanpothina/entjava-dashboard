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
import java.util.Set;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationInfoTest {
    private GenericDao applicationDao;
    private GenericDao errorDao;
    private ApplicationInfo applicationInfo;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        applicationInfo = new ApplicationInfo();
        applicationDao = new GenericDao(Application.class);
        errorDao = new GenericDao(ErrorData.class);
    }

    @Test
    void updateErrors() {
        Application application = (Application) applicationDao.getById(1);
        Set<ErrorData> errorsSet1 =  application.getErrorDataSet();
        applicationInfo.updateErrors("1", applicationDao, errorDao, logger);
        application = (Application) applicationDao.getById(1);
        Set<ErrorData> errorsSet2 =  application.getErrorDataSet();
        assertTrue(errorsSet2.size() >= errorsSet1.size());
    }

    @Test
    void insertApplication() {
        applicationInfo.insertApplication(null, "Application2", "This is application2",
                "1", applicationDao, logger);
        assertTrue(applicationDao.getAll().size() ==2);
    }
    @Test
    void updateApplication() {
        applicationInfo.insertApplication("1", "Application2", "This is application2",
                "1", applicationDao, logger);
        assertTrue(applicationDao.getAll().size() ==1);
    }
}