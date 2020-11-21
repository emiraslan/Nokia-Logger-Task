# Nokia-Logger-Task

## How To Use


```cpp
 // Initialize and configure Logger
        Logger logger = new Logger(
                new Configuration.Builder() // Configuration with Builder pattern
                    .setConsoleConfiguration() // Configure console configuration
                        .setIsLoggingEnabled(false) // default true
                        .setLoggingFormat("%m and %d and %t") // default "ID:%i Date:%d Time:%t Message:'%m' Level:%l"
                        .setLogLevel(LogLevel.DEBUG) // default LogLevel.DEBUG
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
        logger.getErrors().forEach(item -> System.out.println(item.getMessage() + " -- " + item.getLevel().toString()));

```

<br>
