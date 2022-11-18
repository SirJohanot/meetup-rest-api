package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PostMeetupCommand extends AbstractMeetupCommand {

    public PostMeetupCommand(MeetupService meetupService) {
        super(meetupService);
    }

    @Override
    public String execute(HttpServletRequest req) throws ServiceException, IOException, ElementNotFoundException {
        Meetup meetup = getObjectFromRequestBody(req, Meetup.class);
        Integer meetupId = getMeetupIdFromQueryString(req);

        meetupService.updateMeetup(meetup, meetupId);

        return "";
    }
}
