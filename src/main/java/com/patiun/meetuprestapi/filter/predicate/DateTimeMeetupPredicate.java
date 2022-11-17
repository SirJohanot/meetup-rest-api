package com.patiun.meetuprestapi.filter.predicate;

import com.patiun.meetuprestapi.entity.Meetup;

import java.time.LocalDateTime;

public class DateTimeMeetupPredicate extends AbstractMeetupParameterContainsValuePredicate {

    public DateTimeMeetupPredicate(String value) {
        super(value);
    }

    @Override
    protected String getTargetParameterValue(Meetup meetup) {
        LocalDateTime meetupDateTime = meetup.getDateTime();
        return meetupDateTime.toString();
    }
}
