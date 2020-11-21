package com.nokiatask;
import com.nokiatask.configuration.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    private final HashMap<String, LogEntry> logEntries;
    private Configuration configuration;

    public Logger(Configuration configuration) {
        logEntries = new HashMap<>();
        this.configuration = configuration;
    }

    public void log(String message, LogLevel level, Exception source) {
        LogEntry newLogEntry = new LogEntry(message, level, source);
        logEntries.put(newLogEntry.getID(), newLogEntry);
        configuration.recordLogEntry(newLogEntry);
    }

    public void log(String message, LogLevel level) {
        log(message, level, null);
    }

    public List<LogEntry> getErrors(){
        return logEntries
                .values()
                .stream()
                .filter(item ->
                    item.getLevel().ordinal() == LogLevel.ERROR.ordinal())
                .collect(Collectors.toList());
    }

    public void clear(String ID) throws ErrorLogNotFoundException{
        if(!logEntries.containsKey(ID)){
            ErrorLogNotFoundException error =
                    new ErrorLogNotFoundException(String.format("ID %s is not found!", ID));
            log("ID %s is not found!", LogLevel.ERROR, error);
            throw error;
        }

        logEntries.remove(ID);
    }
}
