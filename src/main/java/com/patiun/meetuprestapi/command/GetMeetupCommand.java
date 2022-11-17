package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class GetMeetupCommand extends AbstractMeetupCommand {

    private static final String ID_AT_THE_END_OF_URL_REGEX = ".+/\\d+$";

    public GetMeetupCommand(MeetupService meetupService) {
        super(meetupService);
    }

    @Override
    public String execute(HttpServletRequest req) throws ServiceException, IOException {
        if (Pattern.matches(ID_AT_THE_END_OF_URL_REGEX, req.getRequestURI())) {
            Integer meetupId = getMeetupIdFromQueryString(req);

            Meetup meetup = meetupService.getMeetupById(meetupId);

            return objectToJson(meetup);
        } else {
            List<Meetup> meetups = meetupService.getAllMeetups();

            return objectToJson(meetups);
        }
    }
}
