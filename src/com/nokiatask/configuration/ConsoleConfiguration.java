package com.nokiatask.configuration;

import com.nokiatask.LogEntry;
import com.nokiatask.LogLevel;

public class ConsoleConfiguration extends BaseLoggerConfiguration {
    private ConsoleConfiguration(){
        super();
    }

    @Override
    public boolean saveLogEntry(LogEntry logEntry) {
        if(!isLoggingEnabled) return false;

        try {
            if (logEntry.getLevel().ordinal() >= this.logLevel.ordinal())
                System.out.println(getFormattedLogString(logEntry));
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public static class ConsoleBuilder {
        private final Configuration.Builder builder;
        ConsoleConfiguration consoleConfiguration;

        ConsoleBuilder(Configuration.Builder builder) {
            this.builder = builder;
            this.consoleConfiguration = new ConsoleConfiguration();
        }

        public ConsoleBuilder setIsLoggingEnabled(boolean isLoggingEnabled){
            consoleConfiguration.isLoggingEnabled = isLoggingEnabled;
            return this;
        }

        public ConsoleBuilder setLoggingFormat(String loggingFormat){
            consoleConfiguration.loggingFormat = loggingFormat;
            return this;
        }

        public ConsoleBuilder setLogLevel(LogLevel logLevel){
            consoleConfiguration.logLevel = logLevel;
            return this;
        }

        public Configuration.Builder finishConsoleConfiguration() {
            this.builder.setConsoleConfiguration();
            return this.builder;
        }
    }

}
