# Nokia-Logger-Task

## Task

Statement of the task:

Write a logging library in Java or C++ language.
Log levels are: DEBUG, INFO, WARNING, ERROR in this order.
Required features:
- Provide a log() function with message, log level and source parameters (source can be an exception class in case of ERRORs - so it is an optional parameter)
- Provide a getErrors() function to list all of the active ERROR log entries
- Log entries should have an auto generated unique ID
- Provide a clear() function that expects an ID and sets the corresponding ERROR log entry to be "cleared". It is not listed in the getErrors() anymore. If the ERROR could not be found by the ID, raise an exception and log an ERROR about it
- Make your service externally configurable
	- It can be a config file with a predefined structure
	- It can be a constructor injection
	- etc.	
- Configurable settings:
	- Enable/disable logging to console
	- Set logging format in case of console logging (date-time, message, level)
	- Set the log level in case of console logging (e.g. if log level is DEBUG we will log all of the logs, in case of WARNING, we log the WARNING and the ERROR logs)
	- Enable/disable logging to file
	- Give the filename or filename pattern (e.g. log{0..}.txt means log0.txt, log1.txt, log2.txt, etc.)
	- Set a log rotation (so when a log file is "full" another log file starts)
	- Describe the condition of a log file treated as full
	- Set the number of log files in the rotation (the log.txt should be the active one, and when it is full, should be renamed to log1.txt and a new log.txt should be created)
	- Set the logging format in case of file logging
	- Set the log level in case of file logging
- Create a short documentation about the functions and config rules
- Write clean, well-organized code, create simple API, try to be as OOP as you can


## How To Use


```java
     // Initialize and configure Logger
     Logger logger = new Logger(
          new Configuration.Builder() // Configuration with Builder pattern
              .setConsoleConfiguration() // Configure console configuration
                  .setIsLoggingEnabled(true) // default true
                  .setLoggingFormat("%m and %d and %t and %l") // default "ID:%i Date:%d Time:%t Message:'%m' Level:%l"
                  .setLogLevel(LogLevel.WARNING) // default LogLevel.DEBUG
                  .finishConsoleConfiguration() // returns Configuration.Builder

              .setFileConfiguration() // Configure file configuration
                  .setIsLoggingEnabled(true) // default true
                  .setLoggingFormat("%m and %d and %t") // default "ID:%i Date:%d Time:%t Message:'%m' Level:%l"
                  .setLogLevel(LogLevel.DEBUG) // default LogLevel.DEBUG
                  .setLogRotationLineCount(15) // default 10
                  .setFileNamePattern("log{0}.txt") // default log{0}.txt
                  .setFilePath(System.getProperty("user.dir") + "/res") // default System.getProperty("user.dir")
                  .finishFileConfiguration() // returns Configuration.Builder
              .get() // returns Configuration instance
     );

     // Adding several log records
     for (int i = 0; i < 50; i ++){
         logger.log(String.valueOf(i) + ". I am a log message", LogLevel.INFO);
         logger.log(String.valueOf(i) + ". I am a log message", LogLevel.DEBUG);
         logger.log(String.valueOf(i) + ". I am a log message", LogLevel.WARNING);
         logger.log(String.valueOf(i) + ". I am a log message", LogLevel.ERROR);
     }

     // Printing out only errors
     List<LogEntry> logEntries = logger.getErrors();
     logEntries.forEach(item ->
             System.out.println(
                     item.getID() + " -- " + item.getMessage() + " -- " + item.getLevel().toString())
     );

     // Clearing first error by its ID
     try {
         logger.clear(logEntries.get(0).getID());
     } catch (ErrorLogNotFoundException errorLogNotFoundException) {
         System.out.println("Not found");
     }

     // Re-printing the rest of errors
     logger.getErrors().forEach(item ->
             System.out.println(
                     item.getID() + " -- " + item.getMessage() + " -- " + item.getLevel().toString())
     );

```

<br>
