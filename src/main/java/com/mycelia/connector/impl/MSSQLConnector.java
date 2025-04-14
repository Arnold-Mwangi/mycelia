package com.mycelia.connector.impl;

import com.mycelia.connector.core.AbstractDBConnector;
import com.mycelia.connector.model.QuerySession;
import com.mycelia.connector.model.SchemaObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MSSQLConnector extends AbstractDBConnector {
    public MSSQLConnector(Connection connection) {
        super(connection);
    }

    @Override
    public String getName() {
        return "MSSQL";
    }

    @Override
    public List<QuerySession> getActiveQueries() {
        List<QuerySession> sessions = new ArrayList<QuerySession>();
        String sql = """
            SELECT r.session_id, s.login_name, t.text, r.start_time, r.status, 
                   db_name(r.database_id) AS dbname
            FROM sys.dm_exec_requests r
            JOIN sys.dm_exec_sessions s ON r.session_id = s.session_id
            CROSS APPLY sys.dm_exec_sql_text(r.sql_handle) t
        """;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                QuerySession session = new QuerySession();
                session.setSession_id(rs.getString("session_id"));
                session.setSession_id(rs.getString("login_name"));
                session.setText(rs.getString("text"));
                session.setDatabase(rs.getString("dbname"));
                session.setStatus(rs.getString("status"));
                session.setStart_time(rs.getTimestamp("start_time"));
                sessions.add(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public List<SchemaObject> getSchemaObjects() {
        return List.of();
    }
}
