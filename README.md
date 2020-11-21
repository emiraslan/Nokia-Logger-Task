# Nokia-Logger-Task

## How To Use


```cpp
        Logger logger = new Logger(
                new Configuration.Builder()
                    .setConsoleConfiguration()
                        .setIsLoggingEnabled(false)
                        .setLoggingFormat("%m and %d and %t")
                        .setLogLevel(LogLevel.DEBUG)
                        .finishConsoleConfiguration()

                    .setFileConfiguration()
                        .setIsLoggingEnabled(true)
                        .setLoggingFormat("%m and %d and %t")
                        .setLogLevel(LogLevel.DEBUG)
                        .finishFileConfiguration()
                    .get()
        );

        for (int i = 0; i < 50; i ++){
            logger.log(String.valueOf(i) + ". I am a log message", LogLevel.INFO);
            logger.log(String.valueOf(i) + ". I am a log message", LogLevel.DEBUG);
            logger.log(String.valueOf(i) + ". I am a log message", LogLevel.WARNING);
            logger.log(String.valueOf(i) + ". I am a log message", LogLevel.ERROR);
        }

```

<br>
