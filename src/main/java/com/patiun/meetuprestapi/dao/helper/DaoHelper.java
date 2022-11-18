package com.patiun.meetuprestapi.dao.helper;


import com.patiun.meetuprestapi.connection.ConnectionPool;
import com.patiun.meetuprestapi.connection.ProxyConnection;
import com.patiun.meetuprestapi.dao.MeetupDao;
import com.patiun.meetuprestapi.dao.MeetupDaoImpl;
import com.patiun.meetuprestapi.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws InterruptedException {
        this.connection = pool.getConnection();
    }

    public MeetupDao createMeetupDao() {
        return new MeetupDaoImpl(connection);
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
