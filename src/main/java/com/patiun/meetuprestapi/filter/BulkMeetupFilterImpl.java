package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.filter.predicate.MeetupPredicate;

import java.util.List;
import java.util.Map;

public class BulkMeetupFilterImpl implements BulkMeetupFilter {

    private final MeetupPredicateFactory meetupPredicateFactory = new MeetupPredicateFactory();

    @Override
    public List<Meetup> filter(List<Meetup> meetups, Map<String, String> parameterToValueMap) {
        if (parameterToValueMap == null) {
            return meetups;
        }

        List<MeetupPredicate> meetupPredicates = getMeetupFiltersFromMap(parameterToValueMap);
        return meetups.stream()
                .filter(meetup -> meetupMatchesFiltersOfList(meetup, meetupPredicates))
                .toList();
    }

    private List<MeetupPredicate> getMeetupFiltersFromMap(Map<String, String> filterParametersMap) {
        return filterParametersMap.entrySet()
                .stream()
                .map(e -> {
                    String parameterName = e.getKey();
                    String value = e.getValue();
                    return meetupPredicateFactory.createMeetupFilter(parameterName, value);
                })
                .toList();
    }

    private boolean meetupMatchesFiltersOfList(Meetup meetup, List<MeetupPredicate> meetupPredicates) {
        for (MeetupPredicate filter : meetupPredicates) {
            if (!filter.matches(meetup)) {
                return false;
            }
        }
        return true;
    }
}
