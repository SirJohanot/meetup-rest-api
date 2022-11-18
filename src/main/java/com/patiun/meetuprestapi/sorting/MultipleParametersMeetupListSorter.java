package com.patiun.meetuprestapi.sorting;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MultipleParametersMeetupListSorter implements MeetupListSorter {

    private final MeetupComparatorFactory meetupComparatorFactory = new MeetupComparatorFactory();
    private final List<String> sortingParameters;
    private Optional<Comparator<Meetup>> optionalMeetupComparator = Optional.empty();

    public MultipleParametersMeetupListSorter(List<String> sortingParameters) {
        this.sortingParameters = sortingParameters;
    }

    @Override
    public List<Meetup> sort(List<Meetup> meetups) {
        if (optionalMeetupComparator.isEmpty()) {
            optionalMeetupComparator = getMeetupComparatorFromParameterList(sortingParameters);
        }

        if (optionalMeetupComparator.isPresent()) {
            Comparator<Meetup> meetupComparator = optionalMeetupComparator.get();
            return meetups.stream()
                    .sorted(meetupComparator)
                    .toList();
        }
        return meetups;
    }

    private Optional<Comparator<Meetup>> getMeetupComparatorFromParameterList(List<String> sortingParameterNames) {
        if (sortingParameterNames == null) {
            return Optional.empty();
        }

        return sortingParameterNames.stream()
                .map(meetupComparatorFactory::createParameterMeetupComparator)
                .reduce(Comparator::thenComparing);
    }
}
