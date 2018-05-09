package com.dto;

import java.sql.Timestamp;

public class UserWithTimestamps {
    private String username;
    private String password;
    private Timestamp generationTimestamp;
    private Timestamp lastRequestTimestamp;
    private String uuid;

    public UserWithTimestamps(String username, String password){
        this.username = username;
        this.password = password;
        generationTimestamp = new Timestamp(System.currentTimeMillis());
        lastRequestTimestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getGenerationTimestamp() {
        return generationTimestamp;
    }

    public void setGenerationTimestamp(Timestamp generationTimestamp) {
        this.generationTimestamp = generationTimestamp;
    }

    public Timestamp getLastRequestTimestamp() {
        return lastRequestTimestamp;
    }

    public void setLastRequestTimestamp(Timestamp lastRequestTimestamp) {
        this.lastRequestTimestamp = lastRequestTimestamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
