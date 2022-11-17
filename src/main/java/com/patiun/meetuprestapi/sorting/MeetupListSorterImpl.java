package com.patiun.meetuprestapi.sorting;

import com.patiun.meetuprestapi.entity.Meetup;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MeetupListSorterImpl implements MeetupListSorter {

    private final MeetupComparatorFactory meetupComparatorFactory = new MeetupComparatorFactory();

    @Override
    public List<Meetup> sort(List<Meetup> meetups, List<String> sortingParameters) {
        if (sortingParameters == null) {
            return meetups;
        }
        Optional<Comparator<Meetup>> optionalMeetupComparator = getMeetupComparatorFromParameterList(sortingParameters);

        if (optionalMeetupComparator.isPresent()) {
            Comparator<Meetup> meetupComparator = optionalMeetupComparator.get();
            return meetups.stream()
                    .sorted(meetupComparator)
                    .toList();
        }
        return meetups;
    }

    private Optional<Comparator<Meetup>> getMeetupComparatorFromParameterList(List<String> sortingParameterNames) {
        return sortingParameterNames.stream()
                .map(meetupComparatorFactory::createParameterMeetupComparator)
                .reduce(Comparator::thenComparing);
    }
}
