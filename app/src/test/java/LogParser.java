public class LogParser {
    public String getLogLineContaining(String logResult, String content) {
        String[]logs = logResult.split("\n");
        for (String logLine : logs) {
            if (logLine.contains(content)) {
                return logLine;
            }
        }
        return null;
    }
}
