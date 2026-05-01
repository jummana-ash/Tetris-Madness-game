// TetroBlock.java
package tetris;

import ch.aplu.jgamegrid.*;

public class TetroBlock extends Actor {
    private Location[] relativeLocation = new Location[4];

    // Display a single square in a tetris piece
    public TetroBlock(int blockId, Location[] relativeLocation) {
        super("sprites/tetroblock" + blockId + ".gif");
        this.relativeLocation = relativeLocation.clone();
    }

    public Location getRelativeLocation(int rotationId) {
        // rotation check
        if (rotationId >= relativeLocation.length) {
            return relativeLocation[0];  // non-rotatable pieces
        }
        return relativeLocation[rotationId];

    }

    /**
     * Do not change this method. For testing and marking purpose
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.gameGrid != null) {
            sb.append(this.getLocation());
        }
        return sb.toString();
    }
}
