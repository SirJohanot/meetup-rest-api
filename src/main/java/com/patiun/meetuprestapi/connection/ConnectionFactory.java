package com.patiun.meetuprestapi.connection;


import com.patiun.meetuprestapi.exception.ConnectionFactoryInitialisationException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String CONNECTION_PROPERTIES_FILE_NAME = "databaseConnection.properties";
    private static final String CONNECTION_URL = "db.conn.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private final String databaseConnectionUrl;
    private final String databaseUsername;
    private final String databasePassword;

    public ConnectionFactory() {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONNECTION_PROPERTIES_FILE_NAME);
        try {
            properties.load(inputStream);
            databaseConnectionUrl = properties.getProperty(CONNECTION_URL);
            databaseUsername = properties.getProperty(USERNAME);
            databasePassword = properties.getProperty(PASSWORD);
        } catch (IOException e) {
            throw new ConnectionFactoryInitialisationException(e);
        }
    }

    public ProxyConnection create() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword);
        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }

}
