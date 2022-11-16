package com.patiun.meetuprestapi.service;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.ServiceException;

import java.util.List;

public interface MeetupService {

    List<Meetup> getAllMeetups() throws ServiceException;

    Meetup getMeetupById(Integer id) throws ServiceException;

    void updateMeetup(Meetup meetup, Integer id) throws ServiceException;

    void createMeetup(Meetup meetup) throws ServiceException;

    void deleteMeetup(Integer id) throws ServiceException;

}
