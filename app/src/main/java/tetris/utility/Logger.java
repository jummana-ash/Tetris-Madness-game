package tetris.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    StringBuilder stringBuilder = new StringBuilder();
    private int lineNumber = 1;
    public Logger() {
    }

    /**
     * Log event for testing purpose
     * @param event: the event to log
     */
    public void logEvent(String event) {
        stringBuilder.append(lineNumber + "." + event + "\n");
        lineNumber++;
    }

    public String getAllLog() {
        return stringBuilder.toString();
    }
}
