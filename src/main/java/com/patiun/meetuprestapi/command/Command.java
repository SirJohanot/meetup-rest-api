package com.patiun.meetuprestapi.command;


import com.patiun.meetuprestapi.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
