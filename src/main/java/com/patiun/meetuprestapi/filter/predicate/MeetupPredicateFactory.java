package com.patiun.meetuprestapi.filter.predicate;

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
