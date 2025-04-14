package com.mycelia.connector.core;

import com.mycelia.connector.model.DatabaseInfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public abstract class AbstractDBConnector implements DBConnector {
    protected final Connection connection;

    public AbstractDBConnector(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean testConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException ignored) {}
    }

    @Override
    public DatabaseInfo getDatabaseInfo() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            DatabaseInfo info = new DatabaseInfo();
            info.setName(meta.getDatabaseProductName());
            info.setVersion(meta.getDatabaseProductVersion());
            info.setVendor(meta.getDriverName());
            return info;
        } catch (SQLException e) {
            return null;
        }
    }
}
