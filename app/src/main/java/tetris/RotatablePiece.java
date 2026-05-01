package tetris;

import ch.aplu.jgamegrid.Location;

public abstract class RotatablePiece extends TetrisPiece {

    // Polymorphism
    @Override
    public void rotate() {
        if (isStarting) return;

        int oldRotationId = rotationId;
        rotationId = (rotationId + 1) % 4; // increment and modulate


        // ...
        if (canRotate(rotationId)) {
            for (TetroBlock b : blocks) {
                Location location = new Location(
                        getX() + b.getRelativeLocation(rotationId).x,
                        getY() + b.getRelativeLocation(rotationId).y
                );
                b.setLocation(location);
            }
        } else {
            rotationId = oldRotationId;
        }
    }

    private boolean canRotate(int rotationId) {
        for (TetroBlock b : blocks) {
            int x = getX() + b.getRelativeLocation(rotationId).x;
            int y = getY() + b.getRelativeLocation(rotationId).y;
            Location location = new Location(x, y);

            if (!tetris.isInsideBoundary(location)) return false;

            TetroBlock block = (TetroBlock) gameGrid.getOneActorAt(location, TetroBlock.class);
            if (block != null && !blocks.contains(block)) return false;
        }
        return true;

    }
}
