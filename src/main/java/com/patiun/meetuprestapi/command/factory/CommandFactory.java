package com.patiun.meetuprestapi.command.factory;

import com.patiun.meetuprestapi.command.*;
import com.patiun.meetuprestapi.dao.helper.DaoHelperFactory;
import com.patiun.meetuprestapi.service.MeetupService;
import com.patiun.meetuprestapi.service.MeetupServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        MeetupService meetupService = new MeetupServiceImpl(daoHelperFactory);
        switch (command) {
            case "PUT":
                return new PutMeetupCommand(meetupService);
            case "GET":
                return new GetMeetupCommand(meetupService);
            case "POST":
                return new PostMeetupCommand(meetupService);
            case "DELETE":
                return new DeleteMeetupCommand(meetupService);
            default:
                throw new IllegalArgumentException("Unsupported command: " + command);
        }
    }
}
