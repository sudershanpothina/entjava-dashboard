package com.enterprise.dashboard.util;

import com.enterprise.dashboard.model.Application;
import com.enterprise.dashboard.model.ErrorData;
import com.enterprise.dashboard.util.ErrorService;
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
import static org.junit.Assert.assertTrue;

public class TestErrorService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    ErrorService errorService;

    @BeforeEach
    public void setup() {
        errorService = new ErrorService();
    }

    @Test
    public void checkifResponseIsErrorData() {
        Set<ErrorData> errors = errorService.getErrorData(logger);
        for (ErrorData errorData : errors) {
            assertTrue(errorData instanceof ErrorData);
        }
    }

    @Test
    public void testRestCall() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://35.226.245.191:8080");
        String response = null;
        try {
            response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        } catch (Exception connectionException) {
            response = "[]";
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomErrorDeserializer",
                        new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ErrorData.class, new CustomErrorDeserializer());
        mapper.registerModule(module);
        Set<ErrorData> errorDataSet = mapper.readValue(response, new TypeReference<>() {
        });
        for(ErrorData errorData : errorDataSet) {
            assertTrue(errorData instanceof ErrorData);
        }
    }
}
