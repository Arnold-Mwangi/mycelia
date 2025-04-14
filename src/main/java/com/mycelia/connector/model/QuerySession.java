package com.mycelia.connector.model;

import java.util.Date;

public class QuerySession {
    private String session_id;
    private String login_name;
    private String text;
    private Date start_time;
    private String status;
    private String database;
    public String getSession_id() {
        return session_id;
    }
    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
    public String getLogin_name() {
        return login_name;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getStart_time() {
        return start_time;
    }
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
}
