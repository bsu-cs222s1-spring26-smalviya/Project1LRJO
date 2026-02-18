package edu.bsu.cs;

/**
 * Single Wikipedia revision.
 * Username of editor and timestamp stored in class.
 */

public class Revision {
    private final String user;
    private final String timestamp;

    /**
     * Creates a new Revision with given user and timestamp.
     * @param user name of the editor.
     * @param timestamp the ISO 8601 timestamp of edit
     */
    public Revision(String user, String timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * This getter is for the username of the editor.
     * @return the username
     */

    public String getUser() {
        return user;
    }

    /**
     * This getter is for the timestamp of the edit.
     * @return the ISO 8601 timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }
}