package com.patiun.meetuprestapi.filter.predicate;

import com.patiun.meetuprestapi.entity.Meetup;

public interface MeetupPredicate {

    boolean matches(Meetup meetup);
}
