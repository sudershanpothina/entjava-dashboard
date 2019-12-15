package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.model.Team;
import com.enterprise.dashboard.model.User;
import com.enterprise.dashboard.util.CustomErrorDeserializer;
import com.enterprise.dashboard.utils.Database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.Set;

import static com.enterprise.dashboard.util.ErrorService.getErrorData;
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
//        System.out.println(((Application) applicationDao.getById(3)).getErrorDataSet());
    }

    @Test
    public void getTeamApplication() {
        System.out.println(applicationDao.getByProperty("teamId", "1"));
    }

    @Test
    public void testRestCall() {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8081");
        String response = null;
        try {
            response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        }catch (Exception connectionException) {
            response = "[]";
        }
        ObjectMapper mapper = new ObjectMapper();
        Application application = new Application("Application Test", "This is a test Application", "1");


        SimpleModule module =
                new SimpleModule("CustomErrorDeserializer",
                        new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ErrorData.class, new CustomErrorDeserializer());
        mapper.registerModule(module);
        try {
            Set<ErrorData> errorData = mapper.readValue(response, new TypeReference<>() {});
            application.setErrorDataSet(errorData);
            for(ErrorData error: errorData) {
                System.out.println(error);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        applicationDao.insert(application);
        applicationDao.delete(application);
    }

    @Test
    public void getErrorServiceData() {
        Logger logger = LogManager.getLogger(this.getClass());
        Set<ErrorData> errorDataSet = getErrorData(logger);
        for(ErrorData errorData : errorDataSet) {
            System.out.println(errorData);
        }
    }

}
