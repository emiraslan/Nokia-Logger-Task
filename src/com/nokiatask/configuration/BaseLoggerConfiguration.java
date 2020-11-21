package com.nokiatask.configuration;

import com.nokiatask.LogEntry;
import com.nokiatask.LogLevel;

import java.time.LocalDate;
import java.time.LocalTime;

abstract class BaseLoggerConfiguration {
    protected boolean isLoggingEnabled;
    protected String loggingFormat;
    protected LogLevel logLevel;

    protected BaseLoggerConfiguration() {
        this.isLoggingEnabled = true;
        this.loggingFormat = "ID:%i Date:%d Time:%t Message:'%m' Level:%l";
        this.logLevel = LogLevel.DEBUG;
    }

    protected String getFormattedLogString(LogEntry logEntry){
        return this.loggingFormat.replace("%i", logEntry.getID())
                    .replace("%d", LocalDate.now().toString())
                    .replace("%t", LocalTime.now().toString())
                    .replace("%m", logEntry.getMessage())
                    .replace("%l", logEntry.getLevel().toString());
    }



    abstract public boolean saveLogEntry(LogEntry logEntry);
}
