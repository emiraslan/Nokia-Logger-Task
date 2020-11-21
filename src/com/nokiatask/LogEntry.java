package com.nokiatask;

import java.util.UUID;

public class LogEntry {
    private String ID;
    private LogLevel level;
    private String message;
    private Exception source;

    public LogEntry(String message, LogLevel level) {
        this.ID = UUID.randomUUID().toString();
        this.message = message;
        this.level = level;
    }

    public LogEntry(String message, LogLevel level, Exception source) {
        this(message, level);
        this.source = source;
    }

    public Exception getSource() {
        return source;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getID() {
        return ID;
    }

    public String getMessage() {
        return message;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSource(Exception source) {
        this.source = source;
    }
}
