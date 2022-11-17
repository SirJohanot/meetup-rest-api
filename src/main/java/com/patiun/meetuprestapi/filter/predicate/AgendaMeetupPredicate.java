package com.patiun.meetuprestapi.filter.predicate;

import com.patiun.meetuprestapi.entity.Meetup;

public class AgendaMeetupPredicate extends AbstractMeetupParameterContainsValuePredicate {

    public AgendaMeetupPredicate(String value) {
        super(value);
    }

    @Override
    protected String getTargetParameterValue(Meetup meetup) {
        return meetup.getAgenda();
    }

}
