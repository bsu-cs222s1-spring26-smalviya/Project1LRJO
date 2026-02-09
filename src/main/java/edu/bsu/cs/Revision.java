package edu.bsu.cs;

public class Revision {
    private final String user;
    private final String timestamp;


    public Revision(String user, String timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    private String getUser() {
        return user;
    }

    private String getTimestamp() {
        return timestamp;
    }
}