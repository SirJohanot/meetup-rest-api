package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.filter.predicate.AgendaMeetupPredicate;
import com.patiun.meetuprestapi.filter.predicate.DateTimeMeetupPredicate;
import com.patiun.meetuprestapi.filter.predicate.MeetupPredicate;
import com.patiun.meetuprestapi.filter.predicate.OrganizerMeetupPredicate;

public class MeetupPredicateFactory {

    public MeetupPredicate createMeetupFilter(String parameter, String value) {
        switch (parameter) {
            case "agenda":
                return new AgendaMeetupPredicate(value);
            case "organizer":
                return new OrganizerMeetupPredicate(value);
            case "dateTime":
                return new DateTimeMeetupPredicate(value);
            default:
                throw new IllegalArgumentException("Unsupported filter parameter: " + parameter);
        }
    }
}
