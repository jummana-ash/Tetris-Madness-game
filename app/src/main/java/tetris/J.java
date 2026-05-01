// J.java
package tetris;

import ch.aplu.jgamegrid.Location;

class J extends RotatablePiece {
    private static final BlockPieces PIECE = BlockPieces.J;

    J(Tetris tetris) {
        super();
        this.tetris = tetris;

        Location[][] rotLocs = {
                { new Location(0, 0), new Location(1, 0), new Location(2, 0), new Location(2, 1) },
                { new Location(0, -1), new Location(0, 0), new Location(0, 1), new Location(-1, 1) },
                { new Location(1, 0), new Location(0, 0), new Location(-1, 0), new Location(-1, -1) },
                { new Location(0, 1), new Location(0, 0), new Location(0, -1), new Location(1, -1) }
        };

        for (int i = 0; i < 4; i++) {
            Location[] blockRotations = {
                    rotLocs[0][i], rotLocs[1][i], rotLocs[2][i], rotLocs[3][i]
            };
            blocks.add(new TetroBlock(PIECE.getBlockIndex(), blockRotations));
        }

        // Highlight bounds
        relativeHighlightLocation = new Location[][] {
                { new Location(0, 0), new Location(0, 1) },
                { new Location(1, 0), new Location(1, 1) },
                { new Location(2, 0), new Location(2, 1) }
        };
        absoluteHighlightLocation = new Location[3][2];
    }

    @Override
    public String toString() {
        return "Block: " + PIECE.getBlockName()
                + ". Location: " + getX() + "-" + getY()
                + ". Rotation: " + rotationId;
    }
}