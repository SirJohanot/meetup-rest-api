package com.patiun.meetuprestapi.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMeetupCommand implements Command {

    private static final String MEETUP_ID_FROM_PATH_REGEX = "(?<=/)[\\d+]$";

    protected MeetupService meetupService;

    public AbstractMeetupCommand(MeetupService meetupService) {
        this.meetupService = meetupService;
    }

    protected Meetup getMeetupFromRequestBody(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        BufferedReader requestReader = request.getReader();
        return objectMapper.readValue(requestReader, Meetup.class);
    }

    protected Integer getMeetupIdFromQueryString(HttpServletRequest request) {
        String requestLine = request.getRequestURI();
        Pattern pattern = Pattern.compile(MEETUP_ID_FROM_PATH_REGEX);
        Matcher matcher = pattern.matcher(requestLine);
        String idString = "";
        if (matcher.find()) {
            idString = matcher.group();
        }
        return Integer.valueOf(idString);
    }

    protected String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(object);
    }

}
