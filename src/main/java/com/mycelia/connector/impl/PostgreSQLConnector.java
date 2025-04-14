package com.mycelia.connector.impl;

import com.mycelia.connector.core.AbstractDBConnector;
import com.mycelia.connector.model.QuerySession;
import com.mycelia.connector.model.SchemaObject;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class PostgreSQLConnector extends AbstractDBConnector {

    public PostgreSQLConnector(Connection connection) {
        super(connection);
    }

    @Override
    public String getName() {
        return "PostgreSQL";
    }

    @Override
    public List<QuerySession> getActiveQueries() {
        List<QuerySession> sessions = new ArrayList<>();
        String sql = "SELECT pid, usename, query, state, start_time, datname FROM pg_stat_activity";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                QuerySession qs = new QuerySession();
                qs.setSession_id(rs.getString("pid"));
                qs.setLogin_name(rs.getString("usename"));
                qs.setText(rs.getString("query"));
                qs.setDatabase(rs.getString("datname"));
                qs.setStatus(rs.getString("state"));
                qs.setStart_time(
                        Date.from(rs.getTimestamp("start_time").toInstant()));
                sessions.add(qs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    @Override
    public List<SchemaObject> getSchemaObjects() {
        return List.of(); // implement later
    }
}
