package com.patiun.meetuprestapi.dao.helper;


import com.patiun.meetuprestapi.connection.ConnectionPool;
import com.patiun.meetuprestapi.exception.DaoException;

import java.sql.SQLException;

public class DaoHelperFactory {

    public DaoHelper createHelper() throws DaoException {
        try {
            return new DaoHelper(ConnectionPool.getInstance());
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }
}
