package net.proselyte.jwtappdemo.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Service;

/**
 * Вспомогательный класс для работы с переключением схем hibernate
 */
@Service
public class ConnectionProvider {

    @Autowired
    private UserCredentialsDataSourceAdapter dataSource;

    public Connection getConnection() {
        if (dataSource == null) {
            return null;
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get connection", e);
        }
    }

    public Connection getConnection(String schema) {
        Connection connection = getConnection();
        try {
            connection.createStatement().execute("SET SCHEMA '" + schema + "'");
        } catch (Exception e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException("Could not alter connection to '" + schema + "' schema", e);
        }

        return connection;
    }
}
