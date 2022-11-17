package com.patiun.meetuprestapi.sorting;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.Comparator;

public class MeetupComparatorFactory {

    public Comparator<Meetup> createParameterMeetupComparator(String parameterName) {
        switch (parameterName) {
            case "agenda":
                return Comparator.comparing(Meetup::getAgenda);
            case "organizer":
                return Comparator.comparing(Meetup::getOrganizer);
            case "dateTime":
                return Comparator.comparing(Meetup::getDateTime);
            default:
                throw new IllegalArgumentException("Cannot sort meetups by parameter: " + parameterName);
        }
    }
}
