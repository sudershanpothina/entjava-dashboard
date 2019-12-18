package com.enterprise.dashboard.impl;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.util.ErrorService;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * The type Application info.
 */
public class ApplicationInfo {

    /**
     * Update errors.
     *
     * @param application_id the application id
     * @param applicationDao the application dao
     * @param errorDao       the error dao
     * @param logger         the logger
     */
    public void updateErrors(String application_id, GenericDao applicationDao, GenericDao errorDao, Logger logger) {
        Application application = (Application) applicationDao.getById(Integer.parseInt(application_id));
        Set<ErrorData> errorDataSet = application.getErrorDataSet();
        ErrorService errorService = new ErrorService();
        errorDataSet.addAll(errorService.getErrorData(logger));
        for(ErrorData errorData: application.getErrorDataSet()) {
            errorData.setApplication(application);
            errorDao.saveOrUpdate(errorData);
        }
    }

    /**
     * Insert application.
     *
     * @param id             the id
     * @param name           the name
     * @param description    the description
     * @param teamId         the team id
     * @param applicationDao the application dao
     * @param logger         the logger
     */
    public void insertApplication(String id, String name, String description,
                                  String teamId, GenericDao applicationDao, Logger logger) {
        Application application = new Application(name,
                description, teamId);
        if(id != null) {
            application = (Application) applicationDao.getById(Integer.parseInt(
                    id));
            application.setName(name);
            application.setDescription(description);
            logger.info("updating application");
        }
        logger.error(application);
        applicationDao.saveOrUpdate(application);
    }
}
