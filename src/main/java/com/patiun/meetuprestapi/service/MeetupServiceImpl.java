package com.patiun.meetuprestapi.service;

import com.patiun.meetuprestapi.dao.MeetupDao;
import com.patiun.meetuprestapi.dao.helper.DaoHelper;
import com.patiun.meetuprestapi.dao.helper.DaoHelperFactory;
import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.DaoException;
import com.patiun.meetuprestapi.exception.ElementNotFoundException;
import com.patiun.meetuprestapi.exception.ServiceException;
import com.patiun.meetuprestapi.filter.MeetupFilter;
import com.patiun.meetuprestapi.sorting.MeetupListSorter;

import java.util.List;
import java.util.Optional;

public class MeetupServiceImpl implements MeetupService {

    private final DaoHelperFactory daoHelperFactory;

    public MeetupServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Meetup> getAllMeetups() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createHelper()) {
            MeetupDao meetupDao = daoHelper.createMeetupDao();

            return meetupDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Meetup> getAllMeetupsFilteredAndSorted(MeetupFilter meetupFilter, MeetupListSorter meetupListSorter) throws ServiceException {
        List<Meetup> fetchResults = getAllMeetups();

        fetchResults = meetupFilter.filter(fetchResults);

        fetchResults = meetupListSorter.sort(fetchResults);

        return fetchResults;
    }

    @Override
    public Meetup getMeetupById(Integer id) throws ServiceException, ElementNotFoundException {
        Optional<Meetup> meetupOptional;
        try (DaoHelper daoHelper = daoHelperFactory.createHelper()) {
            MeetupDao meetupDao = daoHelper.createMeetupDao();

            meetupOptional = meetupDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (meetupOptional.isEmpty()) {
            throw new ElementNotFoundException("Could not find a meetup by id = " + id);
        }
        return meetupOptional.get();
    }

    @Override
    public void updateMeetup(Meetup meetup, Integer id) throws ServiceException, ElementNotFoundException {
        try (DaoHelper daoHelper = daoHelperFactory.createHelper()) {
            MeetupDao meetupDao = daoHelper.createMeetupDao();

            throwServiceExceptionIfMeetupByIdDoesNotExist(meetupDao, id);

            meetupDao.update(meetup, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createMeetup(Meetup meetup) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createHelper()) {
            MeetupDao meetupDao = daoHelper.createMeetupDao();

            meetupDao.create(meetup);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteMeetup(Integer id) throws ServiceException, ElementNotFoundException {
        try (DaoHelper daoHelper = daoHelperFactory.createHelper()) {
            MeetupDao meetupDao = daoHelper.createMeetupDao();

            throwServiceExceptionIfMeetupByIdDoesNotExist(meetupDao, id);

            meetupDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void throwServiceExceptionIfMeetupByIdDoesNotExist(MeetupDao meetupDao, Integer id) throws DaoException, ElementNotFoundException {
        Optional<Meetup> meetupOptional = meetupDao.getById(id);
        if (meetupOptional.isEmpty()) {
            throw new ElementNotFoundException("Could not find a meetup by id = " + id);
        }
    }
}
