package com.patiun.meetuprestapi.filter.predicate;

import com.patiun.meetuprestapi.entity.Meetup;

public class OrganizerMeetupPredicate extends AbstractMeetupParameterContainsValuePredicate {

    public OrganizerMeetupPredicate(String value) {
        super(value);
    }

    @Override
    protected String getTargetParameterValue(Meetup meetup) {
        return meetup.getOrganizer();
    }
}
