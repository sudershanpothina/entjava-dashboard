package com.enterprise.dashboard.util;

import com.enterprise.dashboard.model.ErrorData;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;

/**
 * The type Custom error deserializer.
 */
public class CustomErrorDeserializer extends StdDeserializer<ErrorData> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Custom error deserializer.
     */
    public CustomErrorDeserializer() {
        this(null);
    }

    /**
     * Instantiates a new Custom error deserializer.
     *
     * @param vc the vc
     */
    public CustomErrorDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public ErrorData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ErrorData errorData = new ErrorData();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        JsonNode name = node.get("name");
        JsonNode description = node.get("description");
        JsonNode date = node.get("date");
        errorData.setMessage(name.asText());
        errorData.setDescription(description.asText());
        errorData.setDttm(getSqlDate(date.asText(), logger));
        return errorData;
    }
}
