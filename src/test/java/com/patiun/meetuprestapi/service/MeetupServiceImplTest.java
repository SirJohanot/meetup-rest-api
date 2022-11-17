package com.patiun.meetuprestapi.service;

import com.patiun.meetuprestapi.dao.MeetupDao;
import com.patiun.meetuprestapi.dao.helper.DaoHelper;
import com.patiun.meetuprestapi.dao.helper.DaoHelperFactory;
import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.DaoException;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MeetupServiceImplTest {

    private static final List<Meetup> MEETUP_LIST_EXAMPLE = List.of(new Meetup(1, "agenda1", "description1", "organizer1", LocalDateTime.of(1, 1, 1, 1, 1), "location1"),
            new Meetup(2, "agenda2", "description2", "organizer2", LocalDateTime.of(2, 2, 2, 2, 2), "location2"),
            new Meetup(3, "agenda3", "description3", "organizer3", LocalDateTime.of(3, 3, 3, 3, 3), "location3"));

    private static final Meetup MEETUP_EXAMPLE = new Meetup(1, "agenda1", "description1", "organizer1", LocalDateTime.of(1, 1, 1, 1, 1), "location1");

    private MeetupServiceImpl meetupService;
    private DaoHelperFactory daoHelperFactoryMock;
    private DaoHelper daoHelperMock;
    private MeetupDao meetupDaoMock;

    @BeforeEach
    public void setup() throws DaoException {
        meetupDaoMock = mock(MeetupDao.class);

        daoHelperMock = mock(DaoHelper.class);
        when(daoHelperMock.createMeetupDao())
                .thenReturn(meetupDaoMock);

        daoHelperFactoryMock = mock(DaoHelperFactory.class);
        when(daoHelperFactoryMock.createHelper())
                .thenReturn(daoHelperMock);

        meetupService = new MeetupServiceImpl(daoHelperFactoryMock);
    }

    @AfterEach
    public void tearDown() {
        meetupService = null;
        daoHelperFactoryMock = null;
        daoHelperMock = null;
        meetupDaoMock = null;
    }

    @Test
    public void testGetAllMeetupsShouldDelegateToMeetupDao() throws DaoException, ServiceException {
        //given
        when(meetupDaoMock.getAll())
                .thenReturn(MEETUP_LIST_EXAMPLE);
        //when
        List<Meetup> actualMeetupList = meetupService.getAllMeetups();
        //then
        assertEquals(MEETUP_LIST_EXAMPLE, actualMeetupList);
    }

    @Test
    public void testGetAllMeetupsShouldThrowServiceExceptionWhenDaoThrowsException() throws DaoException {
        //given
        when(meetupDaoMock.getAll())
                .thenThrow(DaoException.class);
        //when
        //then
        assertThrows(ServiceException.class, () -> meetupService.getAllMeetups());
    }

    @Test
    public void testGetMeetupByIdShouldDelegateToMeetupDao() throws DaoException, ServiceException, ElementNotFoundException {
        //given
        Integer requestId = 1;
        Optional<Meetup> daoGetByIdResult = Optional.of(MEETUP_EXAMPLE);
        when(meetupDaoMock.getById(requestId))
                .thenReturn(daoGetByIdResult);
        //when
        Meetup actualMeetup = meetupService.getMeetupById(requestId);
        //then
        assertEquals(MEETUP_EXAMPLE, actualMeetup);
    }

    @Test
    public void testGetMeetupByIdShouldThrowServiceExceptionWhenDaoThrowsException() throws DaoException {
        //given
        Integer requestId = 1;
        when(meetupDaoMock.getById(requestId))
                .thenThrow(DaoException.class);
        //when
        //then
        assertThrows(ServiceException.class, () -> meetupService.getMeetupById(requestId));
    }

    @Test
    public void testGetMeetupByIdShouldThrowElementNotFoundExceptionWhenDaoCouldNotFindTheMeetup() throws DaoException {
        //given
        Integer requestId = 1;
        Optional<Meetup> daoGetByIdResult = Optional.empty();
        when(meetupDaoMock.getById(requestId))
                .thenReturn(daoGetByIdResult);
        //when
        //then
        assertThrows(ElementNotFoundException.class, () -> meetupService.getMeetupById(requestId));
    }

    @Test
    public void testCreateMeetupShouldDelegateToMeetupDao() throws DaoException, ServiceException {
        //given
        //when
        meetupService.createMeetup(MEETUP_EXAMPLE);
        //then
        verify(meetupDaoMock, times(1)).create(MEETUP_EXAMPLE);
    }

    @Test
    public void testCreateMeetupShouldThrowServiceExceptionWhenDaoThrowsException() throws DaoException {
        //given
        doThrow(DaoException.class)
                .when(meetupDaoMock)
                .create(MEETUP_EXAMPLE);
        //when
        //then
        assertThrows(ServiceException.class, () -> meetupService.createMeetup(MEETUP_EXAMPLE));
    }

    @Test
    public void testUpdateMeetupShouldDelegateToMeetupDao() throws DaoException, ServiceException, ElementNotFoundException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.of(new Meetup()));
        //when
        meetupService.updateMeetup(MEETUP_EXAMPLE, targetId);
        //then
        verify(meetupDaoMock, times(1)).update(MEETUP_EXAMPLE, targetId);
    }

    @Test
    public void testUpdateMeetupShouldThrowServiceExceptionWhenDaoThrowsException() throws DaoException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.of(new Meetup()));
        doThrow(DaoException.class)
                .when(meetupDaoMock)
                .update(MEETUP_EXAMPLE, targetId);
        //when
        //then
        assertThrows(ServiceException.class, () -> meetupService.updateMeetup(MEETUP_EXAMPLE, targetId));
    }

    @Test
    public void testUpdateMeetupShouldThrowElementNotFoundExceptionWhenDaoCouldNotFindTheMeetup() throws DaoException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ElementNotFoundException.class, () -> meetupService.updateMeetup(MEETUP_EXAMPLE, targetId));
    }

    @Test
    public void testDeleteMeetupShouldDelegateToMeetupDao() throws DaoException, ServiceException, ElementNotFoundException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.of(new Meetup()));
        //when
        meetupService.deleteMeetup(targetId);
        //then
        verify(meetupDaoMock, times(1)).delete(targetId);
    }

    @Test
    public void testDeleteMeetupShouldThrowServiceExceptionWhenDaoThrowsException() throws DaoException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.of(new Meetup()));
        doThrow(DaoException.class)
                .when(meetupDaoMock)
                .delete(targetId);
        //when
        //then
        assertThrows(ServiceException.class, () -> meetupService.deleteMeetup(targetId));
    }

    @Test
    public void testDeleteMeetupShouldThrowElementNotFoundExceptionWhenDaoCouldNotFindTheMeetup() throws DaoException {
        //given
        Integer targetId = 1;
        when(meetupDaoMock.getById(targetId))
                .thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ElementNotFoundException.class, () -> meetupService.deleteMeetup(targetId));
    }
}
