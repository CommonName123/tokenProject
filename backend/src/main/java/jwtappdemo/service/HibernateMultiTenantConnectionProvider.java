package jwtappdemo.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс для настройки переключения схем hibernate
 */
@Component
public class HibernateMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

    @Autowired
    private ConnectionProvider connectionProvider;

    @Override
    public Connection getAnyConnection() {
        return connectionProvider.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String schema) {
        return connectionProvider.getConnection(schema);
    }

    @Override
    public void releaseConnection(String s, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
