package tetris;

import ch.aplu.jgamegrid.Location;


public class Cross extends NonRotatablePiece{
    private static final BlockPieces PIECE = BlockPieces.CROSS;

    Cross(Tetris tetris){
        super();
        this.tetris = tetris;

        // Cross shape{
        Location[] shape = {
                new Location(0, 0),
                new Location(2, 0),
                new Location(1, 1),
                new Location(0, 2),
                new Location(2, 2),
        };

        for (Location l : shape) {
            blocks.add(new TetroBlock(PIECE.getBlockIndex(), new Location[] { l }));
        }

        relativeHighlightLocation = new Location[][]{
                {new Location(0, 0), new Location(0, 1), new Location(0, 2)},
                {new Location(1, 0), new Location(1, 1), new Location(1, 2)},
                {new Location(2, 0), new Location(2, 1), new Location(2, 2)}
        };

        absoluteHighlightLocation = new Location[3][3];
    }

    @Override
    public String toString() {
        return "Block: " + PIECE.getBlockName()
                + ". Location: " + getX() + "-" + getY()
                + ". Rotation: " + rotationId;
    }
}
