package com.patiun.meetuprestapi.dao;

import com.patiun.meetuprestapi.entity.Meetup;
import com.patiun.meetuprestapi.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MeetupDaoImpl implements MeetupDao {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM meetup WHERE id = ?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM meetup;";
    private static final String INSERT_QUERY = "INSERT INTO meetup (agenda, description, organizer, date_time, location) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE meetup SET agenda = ?, description = ?, organizer = ?, date_time = ?, location = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM meetup WHERE id = ?;";

    private final Connection connection;

    public MeetupDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Meetup> getById(Integer id) throws DaoException {
        List<Meetup> results = executeQuery(SELECT_BY_ID_QUERY, List.of(id));
        Optional<Meetup> resultOptional;
        if (results.isEmpty()) {
            resultOptional = Optional.empty();
        } else {
            Meetup result = results.get(0);
            resultOptional = Optional.of(result);
        }
        return resultOptional;
    }

    @Override
    public List<Meetup> getAll() throws DaoException {
        return executeQuery(SELECT_ALL_QUERY, Collections.emptyList());
    }

    @Override
    public void create(Meetup item) throws DaoException {
        executeUpdate(INSERT_QUERY, getMeetupNonIdFields(item));
    }

    @Override
    public void update(Meetup item, Integer id) throws DaoException {
        List<Object> statementParametersList = getMeetupNonIdFields(item);
        statementParametersList.add(id);
        executeUpdate(UPDATE_QUERY, statementParametersList);
    }

    @Override
    public void delete(Integer id) throws DaoException {
        executeUpdate(DELETE_QUERY, List.of(id));
    }

    private void executeUpdate(String query, List<Object> parameters) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatement(query, parameters)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Meetup> executeQuery(String query, List<Object> parameters) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatement(query, parameters);
             ResultSet queryResults = preparedStatement.executeQuery()) {
            return extractResultsFromResultSet(queryResults);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement buildPreparedStatement(String query, List<Object> parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int i = 1;
        for (Object parameter : parameters) {
            preparedStatement.setObject(i++, parameter);
        }
        return preparedStatement;
    }

    private List<Meetup> extractResultsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Meetup> meetups = new ArrayList<>();
        while (resultSet.next()) {
            Meetup meetup = extractMeetupFromResultSetRow(resultSet);
            meetups.add(meetup);
        }
        return meetups;
    }

    private Meetup extractMeetupFromResultSetRow(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");

        String agenda = resultSet.getString("agenda");

        String description = resultSet.getString("description");

        String organizer = resultSet.getString("organizer");

        Timestamp dateTimeTimestamp = resultSet.getTimestamp("date_time");
        LocalDateTime dateTime = timestampToLocalDateTime(dateTimeTimestamp);

        String location = resultSet.getString("location");

        return new Meetup(id, agenda, description, organizer, dateTime, location);
    }

    private LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    private Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private List<Object> getMeetupNonIdFields(Meetup meetup) {
        List<Object> fields = new ArrayList<>();

        String agenda = meetup.getAgenda();
        fields.add(agenda);

        String description = meetup.getDescription();
        fields.add(description);

        String organizer = meetup.getOrganizer();
        fields.add(organizer);

        LocalDateTime dateTime = meetup.getDateTime();
        Timestamp dateTimeTimestamp = localDateTimeToTimestamp(dateTime);
        fields.add(dateTimeTimestamp);

        String location = meetup.getLocation();
        fields.add(location);

        return fields;
    }
}
