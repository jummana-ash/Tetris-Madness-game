// J.java
package tetris;

import ch.aplu.jgamegrid.*;

import java.util.ArrayList;

class J extends Actor {
    private final BlockPieces blockPiece = BlockPieces.J;
    private Location[][] rotationLocation = new Location[4][4];
    private Location[][] relativeHighlightLocations = new Location[3][2];
    private Location[][] absoluteHighlightLocations = new Location[3][2];

    J(Tetris tetris) {
        super();

        this.tetris = tetris;
        // rotationId 0
        rotationLocation[0][0] = new Location(new Location(0, 0));
        rotationLocation[1][0] = new Location(new Location(1, 0));
        rotationLocation[2][0] = new Location(new Location(2, 0));
        rotationLocation[3][0] = new Location(new Location(2, 1));
        // rotationId 1
        rotationLocation[0][1] = new Location(new Location(0, -1));
        rotationLocation[1][1] = new Location(new Location(0, 0));
        rotationLocation[2][1] = new Location(new Location(0, 1));
        rotationLocation[3][1] = new Location(new Location(-1, 1));
        // rotationId 2
        rotationLocation[0][2] = new Location(new Location(1, 0));
        rotationLocation[1][2] = new Location(new Location(0, 0));
        rotationLocation[2][2] = new Location(new Location(-1, 0));
        rotationLocation[3][2] = new Location(new Location(-1, -1));
        // rotationId 3
        rotationLocation[0][3] = new Location(new Location(0, 1));
        rotationLocation[1][3] = new Location(new Location(0, 0));
        rotationLocation[2][3] = new Location(new Location(0, -1));
        rotationLocation[3][3] = new Location(new Location(1, -1));

        for (int i = 0; i < rotationLocation.length; i++)
            blocks.add(new TetroBlock(blockPiece.getBlockIndex(), rotationLocation[i]));

        relativeHighlightLocations[0][0] = new Location(new Location(0, 0));
        relativeHighlightLocations[1][0] = new Location(new Location(1, 0));
        relativeHighlightLocations[2][0] = new Location(new Location(2, 0));
        relativeHighlightLocations[0][1] = new Location(new Location(0, 1));
        relativeHighlightLocations[1][1] = new Location(new Location(1, 1));
        relativeHighlightLocations[2][1] = new Location(new Location(2, 1));
    }

    public String toString() {
        return "Block: " + blockPiece.getBlockName() + ". Location: " + getX() + "-" + getY() + ". Rotation: " + rotationId;
    }

    protected Tetris tetris;
    private boolean isStarting = true;
    private int rotationId = 0;
    private int nb;
    protected ArrayList<TetroBlock> blocks = new ArrayList<TetroBlock>();
    private Actor nextTetrisBlock = null;
    private String autoBlockMove = "";
    private int autoBlockIndex = 0;

    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    /**
     * The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
     */
    public void act() {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc =
                        new Location(getX() + a.getRelativeLocation(0).x, getY() + a.getRelativeLocation(0).y);
                gameGrid.addActor(a, loc);
            }
            hightlightLocation(true);
            isStarting = false;
            nb = 0;
        } else if (canAutoPlay()) {
            autoMove();
        } else {
            setDirection(90);
            if (nb == 1)
                nextTetrisBlock = tetris.createRandomTetrisBlock();

            if (!advance()) {
                if (nb == 0)  // Game is over when tetrisBlock cannot fall down
                    tetris.gameOver();
                else {
                    setActEnabled(false);
                    gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
                    tetris.moveToNextTetris(nextTetrisBlock);
                }
            }
            nb++;
        }

        if (nb == 4) {
            hightlightLocation(false);
        }
    }

    /**
     * Turn on and off highlight to show the surrounding rectangle of a block
     * @param isHighlight turn on or off the highlight
     */
    private void hightlightLocation(boolean isHighlight) {
        for (int i = 0; i < relativeHighlightLocations.length; i++) {
            for (int j = 0; j < relativeHighlightLocations[i].length; j++) {
                if (isHighlight) {
                    Location highlightLocation = relativeHighlightLocations[i][j];
                    Location location = new Location(getX() + highlightLocation.getX(), getY() + highlightLocation.getY());
                    absoluteHighlightLocations[i][j] = location;
                }
                tetris.highlightLocations(absoluteHighlightLocations[i][j], isHighlight);
            }
        }
    }

    /**
     * Based on the input in the properties file, the block can move automatically
     */
    private void autoMove() {
        String moveString = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);
        switch (moveString) {
            case "L":
                left();
                break;
            case "R":
                right();
                break;
            case "T":
                rotate();
                break;
            default:
                break;
        }

        autoBlockIndex++;
    }

    /**
     * Check if the block can be played automatically based on the properties file
     */
    private boolean canAutoPlay() {
        if (autoBlockMove != null && !autoBlockMove.equals("")) {
            if (autoBlockMove.length() > autoBlockIndex) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    void display(GameGrid gg, Location location) {
        for (TetroBlock a : blocks) {
            Location loc =
                    new Location(location.x + a.getRelativeLocation(0).x, location.y + a.getRelativeLocation(0).y);
            gg.addActor(a, loc);
        }
    }

    // Actual actions on the block: move the block left, right, drop and rotate the block
    void left() {
        if (isStarting)
            return;
        setDirection(180);
        advance();
    }

    void right() {
        if (isStarting)
            return;
        setDirection(0);
        advance();
    }

    void rotate() {
        if (isStarting)
            return;

        int oldrotationId = rotationId; // Save it
        rotationId++;
        if (rotationId == 4)
            rotationId = 0;

        if (canRotate(rotationId)) {
            for (TetroBlock a : blocks) {
                Location loc = new Location(getX() + a.getRelativeLocation(rotationId).x, getY() + a.getRelativeLocation(rotationId).y);
                a.setLocation(loc);
            }
        } else
            rotationId = oldrotationId;  // Restore

    }

    void drop() {
        if (isStarting)
            return;
        tetris.speedup();
    }

    private boolean canRotate(int rotationId) {
        // Check for every rotated tetroBlock within the tetrisBlock
        for (TetroBlock a : blocks) {
            int locationX = getX() + a.getRelativeLocation(rotationId).x;
            int locationY = getY() + a.getRelativeLocation(rotationId).y;
            Location loc = new Location(locationX, locationY);
            TetroBlock block =
                    (TetroBlock) (gameGrid.getOneActorAt(loc, TetroBlock.class));

            if (!tetris.isInsideBoundary(loc)) {
                // outside the grid boundary
                return false;
            }
            if (blocks.contains(block)) {
                // in same tetrisBlock->skip
                continue;
            }
            if (block != null) {
                // Another tetroBlock->not permitted
                return false;
            }
        }
        return true;
    }

    // Logic to check if the block has been removed (as winning a line) or drop to the bottom
    private boolean advance() {
        boolean canMove = false;
        for (TetroBlock a : blocks) {
            if (!a.isRemoved()) {
                canMove = true;
            }
        }
        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                continue;
            if (!gameGrid.isInGrid(a.getNextMoveLocation())) {
                canMove = false;
                break;
            }
        }

        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                continue;
            TetroBlock block =
                    (TetroBlock) (gameGrid.getOneActorAt(a.getNextMoveLocation(),
                            TetroBlock.class));
            if (block != null && !blocks.contains(block)) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            move();
            return true;
        }
        return false;
    }

    /**
     * Override Actor.setDirection()
     */
    public void setDirection(double dir) {
        super.setDirection(dir);
        for (TetroBlock a : blocks)
            a.setDirection(dir);
    }

    /**
     * Override Actor.move()
     */
    public void move() {
        if (isRemoved())
            return;
        super.move();
        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                break;
            a.move();
        }
    }

    /**
     * Override Actor.removeSelf()
     */
    public void removeSelf() {
        super.removeSelf();
        for (TetroBlock a : blocks)
            a.removeSelf();
    }
}
