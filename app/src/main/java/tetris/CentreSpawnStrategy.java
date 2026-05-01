package tetris;

import ch.aplu.jgamegrid.Location;

public class CentreSpawnStrategy implements SpawnStrategy {

    @Override
    public SpawnConfig getSpawnConfig() {
        return new SpawnConfig(new Location(6, 0), 1);
    }
}
