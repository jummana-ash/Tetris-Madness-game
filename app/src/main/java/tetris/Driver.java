package tetris;

import ch.aplu.jgamegrid.Actor;
import tetris.utility.PropertiesLoader;

import java.awt.*;
import java.util.Arrays;
import java.util.Properties;

public class Driver {
    public static final String DEFAULT_PROPERTIES_PATH = "properties/game1.properties";

    /**
     * Starting point
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        String propertiesPath = DEFAULT_PROPERTIES_PATH;
        if (args.length > 0) {
            propertiesPath = args[0];
        }
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        Tetris game = new Tetris(properties);
        String log = game.runApp();
        System.out.println("log = " + log);
    }
}
