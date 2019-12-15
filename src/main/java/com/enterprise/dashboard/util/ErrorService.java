package com.enterprise.dashboard.util;

import com.enterprise.dashboard.model.ErrorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class ErrorService {

    public static Set<ErrorData> getErrorData(Logger logger) {

        Set<ErrorData> errorData = null;
        Client client = ClientBuilder.newClient();

        WebTarget target =
                client.target("http://35.226.245.191:8080");
        String response = null;

        try {
            response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        }catch (Exception connectionException) {
            response = "[]";
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomErrorDeserializer",
                        new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ErrorData.class, new CustomErrorDeserializer());
        mapper.registerModule(module);

        try {
           errorData = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            logger.error(e);
        }
        return errorData;
    }
}
