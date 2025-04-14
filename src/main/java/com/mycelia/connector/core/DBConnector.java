package com.mycelia.connector.core;

import com.mycelia.connector.model.DatabaseInfo;
import com.mycelia.connector.model.QuerySession;
import com.mycelia.connector.model.SchemaObject;

import java.util.List;

public interface DBConnector {
    String getName();
    boolean testConnection();
    List<QuerySession> getActiveQueries();
    DatabaseInfo getDatabaseInfo();
    List<SchemaObject> getSchemaObjects();
    void close();
}
