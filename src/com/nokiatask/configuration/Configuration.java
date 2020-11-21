package com.nokiatask.configuration;

import com.nokiatask.LogEntry;
import com.nokiatask.LogLevel;

public class Configuration {
    private ConsoleConfiguration consoleConfiguration;
    private FileConfiguration fileConfiguration;

    private Configuration(){
    }

    public void recordLogEntry(LogEntry logEntry) {
        consoleConfiguration.saveLogEntry(logEntry);
        fileConfiguration.saveLogEntry(logEntry);
    }

    public static class Builder {
        private Configuration configuration;
        private ConsoleConfiguration.ConsoleBuilder consoleBuilder;
        private FileConfiguration.FileBuilder fileBuilder;

        public Builder(){
            this.configuration = new Configuration();
            this.consoleBuilder = new ConsoleConfiguration.ConsoleBuilder(this);
            this.fileBuilder = new FileConfiguration.FileBuilder(this);
        }

        public ConsoleConfiguration.ConsoleBuilder setConsoleConfiguration() {
            this.configuration.consoleConfiguration = consoleBuilder.consoleConfiguration;
            return consoleBuilder;
        }

        public FileConfiguration.FileBuilder setFileConfiguration() {
            this.configuration.fileConfiguration = fileBuilder.fileConfiguration;
            return fileBuilder;
        }

        public Configuration get(){
            return this.configuration;
        }
    }
}
