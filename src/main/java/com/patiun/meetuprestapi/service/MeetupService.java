package com.patiun.meetuprestapi.service;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.filter.MeetupFilter;
import com.patiun.meetuprestapi.sorting.MeetupListSorter;

import java.util.List;

public interface MeetupService {

    List<Meetup> getAllMeetups() throws ServiceException;

    List<Meetup> getAllMeetupsFilteredAndSorted(MeetupFilter meetupFilter, MeetupListSorter meetupListSorter) throws ServiceException;

    Meetup getMeetupById(Integer id) throws ServiceException, ElementNotFoundException;

    void updateMeetup(Meetup meetup, Integer id) throws ServiceException, ElementNotFoundException;

    void createMeetup(Meetup meetup) throws ServiceException;

    void deleteMeetup(Integer id) throws ServiceException, ElementNotFoundException;

}
