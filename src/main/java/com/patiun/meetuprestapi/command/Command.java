package com.patiun.meetuprestapi.command;


import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {

    String execute(HttpServletRequest req) throws ServiceException, IOException, ElementNotFoundException;
}
