package tetris;

import ch.aplu.jgamegrid.Location;

public class SpawnConfig {
    public final Location spawnLocation;
    public final int fallSpeed;

    public SpawnConfig(Location spawnLocation, int fallSpeed) {

        this.spawnLocation = spawnLocation;
        this.fallSpeed = fallSpeed;
    }
}
