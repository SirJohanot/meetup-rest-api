package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMeetupCommand extends AbstractRestCommand {

    private static final String MEETUP_ID_FROM_PATH_REGEX = "(?<=/)\\d+$";

    protected MeetupService meetupService;

    public AbstractMeetupCommand(MeetupService meetupService) {
        this.meetupService = meetupService;
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

}
