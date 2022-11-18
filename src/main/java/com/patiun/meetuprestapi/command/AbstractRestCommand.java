package com.patiun.meetuprestapi.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractRestCommand implements Command {

    protected <T> T getObjectFromRequestBody(HttpServletRequest request, Class<T> objectClass) throws IOException {
        ObjectMapper objectMapper = buildObjectMapper();

        BufferedReader requestReader = request.getReader();

        return objectMapper.readValue(requestReader, objectClass);
    }

    protected String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = buildObjectMapper();

        return objectMapper.writeValueAsString(object);
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }
}
