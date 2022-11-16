package com.patiun.meetuprestapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patiun.meetuprestapi.dao.helper.DaoHelperFactory;
import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.service.MeetupService;
import com.patiun.meetuprestapi.service.MeetupServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeetupController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meetup meetup = requestBodyToMeetup(req);
        MeetupService meetupService = new MeetupServiceImpl(new DaoHelperFactory());
        meetupService.createMeetup(meetup);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer meetupId = getMeetupIdFromQueryString(req);

        MeetupService meetupService = new MeetupServiceImpl(new DaoHelperFactory());
        Meetup meetup = meetupService.getMeetupById(meetupId);

        writeJsonToResponse(objectToJson(meetup), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meetup meetup = requestBodyToMeetup(req);

        Integer meetupId = getMeetupIdFromQueryString(req);

        MeetupService meetupService = new MeetupServiceImpl(new DaoHelperFactory());
        meetupService.updateMeetup(meetup, meetupId);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer meetupId = getMeetupIdFromQueryString(req);

        MeetupService meetupService = new MeetupServiceImpl(new DaoHelperFactory());
        meetupService.deleteMeetup(meetupId);
    }

    private Meetup requestBodyToMeetup(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader requestReader = request.getReader();
        return objectMapper.readValue(requestReader, Meetup.class);
    }

    private Integer getMeetupIdFromQueryString(HttpServletRequest request) {
        String requestLine = request.getRequestURI();
        Pattern pattern = Pattern.compile("(?<=/)[\\d+]$");
        Matcher matcher = pattern.matcher(requestLine);
        String idString = matcher.group(1);
        return Integer.valueOf(idString);
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    private void writeJsonToResponse(String json, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
