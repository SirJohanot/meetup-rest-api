package com.patiun.meetuprestapi.sorting;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.List;

public interface MeetupListSorter {

    List<Meetup> sort(List<Meetup> meetups);
}
