//O.java
package tetris;

import ch.aplu.jgamegrid.Location;

class O extends RotatablePiece {
    private static final BlockPieces PIECE = BlockPieces.O;

    O(Tetris tetris) {
        super();
        this.tetris = tetris;

        // O piece doesn't visually rotate but keeps 4 identical states
        Location[][] rotLocs = {
                { new Location(0, 0), new Location(1, 0), new Location(1, 1), new Location(0, 1) },
                { new Location(0, 0), new Location(1, 0), new Location(1, 1), new Location(0, 1) },
                { new Location(0, 0), new Location(1, 0), new Location(1, 1), new Location(0, 1) },
                { new Location(0, 0), new Location(1, 0), new Location(1, 1), new Location(0, 1) }
        };

        for (int i = 0; i < 4; i++) {
            blocks.add(new TetroBlock(PIECE.getBlockIndex(), new Location[]{
                    rotLocs[0][i], rotLocs[1][i], rotLocs[2][i], rotLocs[3][i]
            }));
        }

        // Highlight bounds
        relativeHighlightLocation = new Location[][] {
                { new Location(0, 0), new Location(0, 1) },
                { new Location(1, 0), new Location(1, 1) }
        };
        absoluteHighlightLocation = new Location[2][2];
    }

    @Override
    public String toString() {
        return "Block: " + PIECE.getBlockName()
                + ". Location: " + getX() + "-" + getY()
                + ". Rotation: " + rotationId;
    }
}