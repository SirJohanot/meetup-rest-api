package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PutMeetupCommand extends AbstractMeetupCommand {

    public PutMeetupCommand(MeetupService meetupService) {
        super(meetupService);
    }

    @Override
    public String execute(HttpServletRequest req) throws ServiceException, IOException {
        Meetup meetup = getMeetupFromRequestBody(req);

        meetupService.createMeetup(meetup);

        return "";
    }
}
