package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.service.MeetupService;

import javax.servlet.http.HttpServletRequest;

public class DeleteMeetupCommand extends AbstractMeetupCommand {

    public DeleteMeetupCommand(MeetupService meetupService) {
        super(meetupService);
    }

    @Override
    public String execute(HttpServletRequest req) throws ServiceException {
        Integer meetupId = getMeetupIdFromQueryString(req);

        meetupService.deleteMeetup(meetupId);
        
        return "";
    }
}
