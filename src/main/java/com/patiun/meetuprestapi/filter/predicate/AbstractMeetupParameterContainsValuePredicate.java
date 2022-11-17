package com.patiun.meetuprestapi.filter.predicate;

import com.patiun.meetuprestapi.entity.Meetup;

public abstract class AbstractMeetupParameterContainsValuePredicate implements MeetupPredicate {

    private final String value;

    public AbstractMeetupParameterContainsValuePredicate(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(Meetup meetup) {
        return getTargetParameterValue(meetup).contains(value);
    }

    protected abstract String getTargetParameterValue(Meetup meetup);
}
