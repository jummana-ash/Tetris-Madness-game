package tetris;

import ch.aplu.jgamegrid.Location;
import java.util.Properties;
import java.util.Random;

// Feature 2 ON: random spawn position + variable fall speed
// GRASP: Protected Variations , it has the same interface as CentreSpawnStrategy
public class RandomSpawnStrategy implements SpawnStrategy {

    private Location[] locations;
    private int[] speeds;
    private int index = 0;
    private final Random random;
    private final boolean isAuto;

    public RandomSpawnStrategy(Properties properties) {
        this.isAuto = Boolean.parseBoolean(properties.getProperty("isAuto", "false"));
        this.random = new Random(30006);

        // Entries are ; separated; x and y are - separated, speed is , seperated
        String locationsProp = properties.getProperty("locations", "").trim();
        if (!locationsProp.isEmpty()) {
            String[] locStrings = locationsProp.split(";");
            locations = new Location[locStrings.length];
            for (int i = 0; i < locStrings.length; i++) {
                locations[i] = parseLocation(locStrings[i].trim());
            }
        }

        String speedProp = properties.getProperty("speed", "").trim();
        if (!speedProp.isEmpty()) {
            String[] speedStrings = speedProp.split(",");
            speeds = new int[speedStrings.length];
            for (int i = 0; i < speedStrings.length; i++) {
                speeds[i] = Integer.parseInt(speedStrings[i].trim());
            }
        }
    }

    // Parse a single x-y location
    // split it by dash operatoe
    // GRASP Information Expert
    //  - this class owns its own config format

    private Location parseLocation(String entry) {
        int searchFrom = entry.startsWith("-") ? 1 : 0;
        int dashIndex  = entry.indexOf('-', searchFrom);
        if (dashIndex > 0) {
            int x = Integer.parseInt(entry.substring(0, dashIndex).trim());
            int y = Integer.parseInt(entry.substring(dashIndex + 1).trim());
            return new Location(x, y);
        }
        return new Location(0, 0); // safe fallback
    }

    @Override
    public SpawnConfig getSpawnConfig() {
        Location location;
        int speed;

        // Auto mode
        if (isAuto && locations != null && index < locations.length) {
            location = locations[index];
            speed = (speeds != null && index < speeds.length) ? speeds[index] : 1;
            index++;
        } else {

            location = new Location(random.nextInt(12), 0);
            speed = random.nextInt(3) + 1;
        }

        return new SpawnConfig(location, speed);
    }
}