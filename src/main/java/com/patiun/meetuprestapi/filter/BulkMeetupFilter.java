package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.filter.predicate.MeetupPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BulkMeetupFilter implements MeetupFilter {

    private final MeetupPredicateFactory meetupPredicateFactory = new MeetupPredicateFactory();
    private final Map<String, String> filterPredicatesMap;
    private List<MeetupPredicate> meetupPredicates;

    public BulkMeetupFilter(Map<String, String> filterPredicatesMap) {
        this.filterPredicatesMap = filterPredicatesMap;
    }

    @Override
    public List<Meetup> filter(List<Meetup> meetups) {
        if (meetupPredicates == null) {
            meetupPredicates = getMeetupPredicatesFromMap(filterPredicatesMap);
        }

        return meetups.stream()
                .filter(meetup -> meetupMatchesPredicatesOfList(meetup, meetupPredicates))
                .toList();
    }

    private List<MeetupPredicate> getMeetupPredicatesFromMap(Map<String, String> filterParametersMap) {
        if (filterParametersMap == null) {
            return new ArrayList<>();
        }

        return filterParametersMap.entrySet()
                .stream()
                .map(e -> {
                    String parameterName = e.getKey();
                    String value = e.getValue();
                    return meetupPredicateFactory.createMeetupFilter(parameterName, value);
                })
                .toList();
    }

    private boolean meetupMatchesPredicatesOfList(Meetup meetup, List<MeetupPredicate> meetupPredicates) {
        for (MeetupPredicate filter : meetupPredicates) {
            if (!filter.matches(meetup)) {
                return false;
            }
        }
        return true;
    }
}
