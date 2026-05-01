package tetris;

import ch.aplu.jgamegrid.Location;

class Z extends RotatablePiece {
    private static final BlockPieces PIECE = BlockPieces.Z;

    Z(Tetris tetris) {
        super();
        this.tetris = tetris;

        // map locations by each rotation
        Location[][] rotLocs = {

                {new Location(0, 0), new Location(1, 0), new Location(1, 1), new Location(2, 1)},
                {new Location(0, -1), new Location(0, 0), new Location(-1, 0), new Location(-1, 1)},
                {new Location(1, 0), new Location(0, 0), new Location(0, -1), new Location(-1, -1)},
                {new Location(0, 1), new Location(0, 0), new Location(1, 0), new Location(1, -1)}
        };

        for (int i = 0; i < 4; i++) {
            blocks.add(new TetroBlock(PIECE.getBlockIndex(), new Location[]{
                    rotLocs[0][i], rotLocs[1][i], rotLocs[2][i], rotLocs[3][i]
            }));
        }

        // Highlight bounding box: 3 wide × 2 tall
        relativeHighlightLocation = new Location[][]{
                {new Location(0, 0), new Location(0, 1)},
                {new Location(1, 0), new Location(1, 1)},
                {new Location(2, 0), new Location(2, 1)}
        };
        absoluteHighlightLocation = new Location[3][2];
    }
}