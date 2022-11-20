package com.patiun.meetuprestapi.filter;

import com.patiun.meetuprestapi.entity.Meetup;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulkMeetupFilterTest {

    private static final Meetup SAMPLE_MEETUP_ONE = new Meetup(1, "General Meeting", "We discuss the current development progress", "Team Lead", LocalDateTime.of(2023, 7, 13, 9, 0), "Conference Room");
    private static final Meetup SAMPLE_MEETUP_TWO = new Meetup(2, "Stand-up Meeting", "We each share our progress on the new feature so far", "Senior Dev", LocalDateTime.of(2023, 7, 21, 13, 45), "Online");
    private static final Meetup SAMPLE_MEETUP_THREE = new Meetup(3, "Finance Meeting", "We discuss the company's profit", "CEO", LocalDateTime.of(2023, 3, 13, 9, 0), "Directors' Meeting Room");
    private static final Meetup SAMPLE_MEETUP_FOUR = new Meetup(4, "Mundane Meeting", "a boring meetup", "some guy", LocalDateTime.of(2023, 9, 13, 9, 0), "some boring room");
    private static final Meetup SAMPLE_MEETUP_FIVE = new Meetup(5, "Exciting Get-together", "we gather to celebrate the company's anniversary", "Tech Director", LocalDateTime.of(2023, 9, 25, 9, 0), "Party Room");

    private static final List<Meetup> SAMPLE_MEETUP_LIST = Arrays.asList(SAMPLE_MEETUP_ONE, SAMPLE_MEETUP_TWO, SAMPLE_MEETUP_THREE, SAMPLE_MEETUP_FOUR, SAMPLE_MEETUP_FIVE);

    private static final Map<String, String> AGENDA_CONTAINS_MEETING_DATE_TIME_CONTAINS_NINE_FILTER_MAP = Map.of(
            "agenda", "Meeting",
            "dateTime", "09");

    private static final Map<String, String> AGENDA_CONTAINS_EXCITING_ORGANIZER_CONTAINS_MANAGER = Map.of(
            "agenda", "Exciting",
            "organizer", "manager");

    @Test
    public void testFilterShouldReturnMeetupsThatContainMatchingParameterValuesWhenThereAreMeetupsThatFitTheParameters() {
        //given
        BulkMeetupFilter bulkMeetupFilter = new BulkMeetupFilter(AGENDA_CONTAINS_MEETING_DATE_TIME_CONTAINS_NINE_FILTER_MAP);
        List<Meetup> expectedFilteredList = List.of(SAMPLE_MEETUP_ONE, SAMPLE_MEETUP_THREE, SAMPLE_MEETUP_FOUR);
        //when
        List<Meetup> actualFilteredList = bulkMeetupFilter.filter(SAMPLE_MEETUP_LIST);
        //then
        assertEquals(expectedFilteredList, actualFilteredList);
    }

    @Test
    public void testFilterShouldReturnAnEmptyListWhenThereAreNoMeetupsThatFitTheParameters() {
        //given
        BulkMeetupFilter bulkMeetupFilter = new BulkMeetupFilter(AGENDA_CONTAINS_EXCITING_ORGANIZER_CONTAINS_MANAGER);
        List<Meetup> expectedFilteredList = Collections.emptyList();
        //when
        List<Meetup> actualFilteredList = bulkMeetupFilter.filter(SAMPLE_MEETUP_LIST);
        //then
        assertEquals(expectedFilteredList, actualFilteredList);
    }

    @Test
    public void testFilterShouldReturnTheGivenListWhenTheFilterMapIsEmpty() {
        //given
        BulkMeetupFilter bulkMeetupFilter = new BulkMeetupFilter(Collections.emptyMap());
        //when
        List<Meetup> actualFilteredList = bulkMeetupFilter.filter(SAMPLE_MEETUP_LIST);
        //then
        assertEquals(SAMPLE_MEETUP_LIST, actualFilteredList);
    }

    @Test
    public void testFilterShouldReturnTheGivenListWhenTheFilterMapIsNull() {
        //given
        BulkMeetupFilter bulkMeetupFilter = new BulkMeetupFilter(null);
        //when
        List<Meetup> actualFilteredList = bulkMeetupFilter.filter(SAMPLE_MEETUP_LIST);
        //then
        assertEquals(SAMPLE_MEETUP_LIST, actualFilteredList);
    }
}
