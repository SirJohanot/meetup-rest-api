package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.List;
import java.util.Map;

public interface BulkMeetupFilter {

    List<Meetup> filter(List<Meetup> meetups, Map<String, String> parameterToValueMap);
}
