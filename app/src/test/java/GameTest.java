
import org.junit.Assert;
import org.junit.Test;
import tetris.Tetris;
import tetris.utility.PropertiesLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class GameTest {
    @Test
    public void test0DefaultBehavior() {
        String propertiesPath = "properties/test0.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String logResult = game.runApp();
        System.out.println("logResult = " + logResult);
        Assert.assertTrue(logResult.contains("Block: I. Location: 0-29. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: S. Location: 4-28. Rotation: 1"));
        Assert.assertTrue(logResult.contains("Block: O. Location: 5-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 8-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Score: 1"));
        Assert.assertTrue(logResult.contains("Score: 2"));
    }
    @Test
    public void test1Feature1() {
        String propertiesPath = "properties/test1.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String logResult = game.runApp();
        System.out.println("logResult = " + logResult);
        Assert.assertTrue(logResult.contains("Block: O. Location: 0-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 3-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 6-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 11-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: +. Location: 1-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: X. Location: 5-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: /. Location: 8-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Score: 1"));
        Assert.assertTrue(logResult.contains("Score: 2"));
    }

    @Test
    public void test2Feature2() {
        String propertiesPath = "properties/test2.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String logResult = game.runApp();
        System.out.println("logResult = " + logResult);
        Assert.assertTrue(logResult.contains("Block: I. Location: 0-29. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: S. Location: 4-28. Rotation: 1"));
        Assert.assertTrue(logResult.contains("Block: O. Location: 5-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 8-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Score: 1"));
        Assert.assertTrue(logResult.contains("Score: 2"));

        Assert.assertFalse(logResult.contains("Block: I. Location: 3-0. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: I. Location: 0-2. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: I. Location: 0-5. Rotation: 0"));

        Assert.assertTrue(logResult.contains("Block: S. Location: 4-5. Rotation: 1"));
        Assert.assertFalse(logResult.contains("Block: S. Location: 4-6. Rotation: 1"));
        Assert.assertTrue(logResult.contains("Block: S. Location: 4-7. Rotation: 1"));

        Assert.assertTrue(logResult.contains("Block: O. Location: 7-0. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: O. Location: 5-0. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: O. Location: 5-1. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: O. Location: 5-2. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: O. Location: 5-3. Rotation: 0"));
    }

    @Test
    public void test3Feature3() {
        String propertiesPath = "properties/test3.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String logResult = game.runApp();
        System.out.println("logResult = " + logResult);
        String statisticsLog = getStatisticsLog();
        Assert.assertTrue(statisticsLog.contains("Score: 2"));
        Assert.assertTrue(statisticsLog.contains("I: 11"));
        Assert.assertTrue(statisticsLog.contains("S: 1"));
        Assert.assertTrue(statisticsLog.contains("O: 1"));
        Assert.assertTrue(statisticsLog.contains("T: 4"));
    }

    private static String getStatisticsLog() {
        String statisticsFilePath = "statistics.txt";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(statisticsFilePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder statisticsLogBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            statisticsLogBuilder.append(scanner.nextLine()).append("\n");
        }
        return statisticsLogBuilder.toString();
    }

    @Test
    public void test4Feature123() {
        String propertiesPath = "properties/test4.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String logResult = game.runApp();
        System.out.println("logResult = " + logResult);
        String statisticsLog = getStatisticsLog();

        Assert.assertTrue(logResult.contains("Block: O. Location: 0-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 3-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 6-29. Rotation: 2"));
        Assert.assertTrue(logResult.contains("Block: T. Location: 11-28. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: +. Location: 1-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: X. Location: 5-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: /. Location: 8-27. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Score: 1"));
        Assert.assertTrue(logResult.contains("Score: 2"));

        Assert.assertTrue(logResult.contains("Block: +. Location: 1-5. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: +. Location: 1-6. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: +. Location: 1-7. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: +. Location: 1-8. Rotation: 0"));

        Assert.assertTrue(logResult.contains("Block: X. Location: 5-0. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: X. Location: 5-1. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: X. Location: 5-2. Rotation: 0"));

        Assert.assertTrue(logResult.contains("Block: /. Location: 8-3. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: /. Location: 8-4. Rotation: 0"));
        Assert.assertFalse(logResult.contains("Block: /. Location: 8-5. Rotation: 0"));
        Assert.assertTrue(logResult.contains("Block: /. Location: 8-6. Rotation: 0"));

        Assert.assertTrue(statisticsLog.contains("Score: 2"));
        Assert.assertTrue(statisticsLog.contains("I: 9"));
        Assert.assertTrue(statisticsLog.contains("S: 1"));
        Assert.assertTrue(statisticsLog.contains("O: 2"));
        Assert.assertTrue(statisticsLog.contains("T: 4"));
        Assert.assertTrue(statisticsLog.contains("X: 1"));
        Assert.assertTrue(statisticsLog.contains("+: 1"));
        Assert.assertTrue(statisticsLog.contains("/: 1"));
    }
}
