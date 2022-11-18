package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.List;

public interface MeetupFilter {

    List<Meetup> filter(List<Meetup> meetups);
}
