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
    private static final String CONNECTOR_CLASS = "db.connector.class";
    private static final String CONNECTION_URL = "db.connection.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private final String databaseConnectionUrl;
    private final String databaseUsername;
    private final String databasePassword;

    public ConnectionFactory() {
        Properties databaseProperties = new Properties();
        InputStream propertiesFileInputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(CONNECTION_PROPERTIES_FILE_NAME);
        try {
            databaseProperties.load(propertiesFileInputStream);

            String databaseConnectorClassName = databaseProperties.getProperty(CONNECTOR_CLASS);
            Class.forName(databaseConnectorClassName);

            databaseConnectionUrl = databaseProperties.getProperty(CONNECTION_URL);
            databaseUsername = databaseProperties.getProperty(USERNAME);
            databasePassword = databaseProperties.getProperty(PASSWORD);
        } catch (IOException | ClassNotFoundException e) {
            throw new ConnectionFactoryInitialisationException(e);
        }
    }

    public ProxyConnection create() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword);
        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }

}
