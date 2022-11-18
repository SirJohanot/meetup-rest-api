package com.patiun.meetuprestapi.command;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.entity.RequestParameters;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.filter.BulkMeetupFilter;
import com.patiun.meetuprestapi.service.MeetupService;
import com.patiun.meetuprestapi.sorting.MultipleParametersMeetupListSorter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class GetMeetupCommand extends AbstractMeetupCommand {

    private static final String ID_AT_THE_END_OF_URL_REGEX = ".+/\\d+$";

    public GetMeetupCommand(MeetupService meetupService) {
        super(meetupService);
    }

    @Override
    public String execute(HttpServletRequest req) throws ServiceException, IOException, ElementNotFoundException {
        if (Pattern.matches(ID_AT_THE_END_OF_URL_REGEX, req.getRequestURI())) {
            Integer meetupId = getMeetupIdFromQueryString(req);

            Meetup result = meetupService.getMeetupById(meetupId);

            return objectToJson(result);
        } else {
            List<Meetup> results;

            if (req.getContentLength() > 0) {
                RequestParameters requestParameters = getObjectFromRequestBody(req, RequestParameters.class);
                Map<String, String> filterParameters = requestParameters.getFilterParameters();
                List<String> sortingParameters = requestParameters.getSortingParameters();
                results = meetupService.getAllMeetupsFilteredAndSorted(new BulkMeetupFilter(filterParameters), new MultipleParametersMeetupListSorter(sortingParameters));
            } else {
                results = meetupService.getAllMeetups();
            }

            return objectToJson(results);
        }
    }
}
