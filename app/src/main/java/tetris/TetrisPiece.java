package tetris;

import ch.aplu.jgamegrid.*;
import java.util.ArrayList;

public abstract class TetrisPiece extends Actor {

    // Fields to generalise each piece
    protected Tetris tetris;
    protected Boolean isStarting = true;
    protected int rotationId = 0;
    protected int nb = 0;
    protected int speed = 1;
    protected Location spawnLocation = new Location(6, 0); // Default spawn location
    protected ArrayList<TetroBlock> blocks = new ArrayList<>();
    protected Actor nextTetrisBlock = null;
    protected String autoBlockMove = "";
    protected int autoBlockIndex = 0;

    // Highlight arrays
    protected Location[][] relativeHighlightLocation;
    protected Location[][] absoluteHighlightLocation;


    public abstract void rotate();

    // gets rid of all the repitition
    @Override
    public void act() {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc = new Location(
                        getX() + a.getRelativeLocation(0).x,
                        getY() + a.getRelativeLocation(0).y
                );
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

            // Feature 2 - advance speed
            int advanced = 0;
            for (int i = 0; i < speed; i++) {
                if (!advance()) {
                    break;
                }
                advanced++;
            }

            // Only landed if the piece cant move at all
            if (advanced == 0) {
                if (nb == 0) {
                    tetris.gameOver();
                } else {
                    setActEnabled(false);
                    TetrisPiece nextPiece = (TetrisPiece) nextTetrisBlock;
                    gameGrid.addActor(nextPiece, nextPiece.spawnLocation);
                    tetris.moveToNextTetris(nextPiece);
                }
            }
            nb++;
        }

        if (nb == 4) {
            hightlightLocation(false);
        }
    }

    // Highlightssss
    private void hightlightLocation(boolean isHighlight) {
        if (relativeHighlightLocation == null) return;
        for (int i = 0; i < relativeHighlightLocation.length; i++) {
            for (int j = 0; j < relativeHighlightLocation[i].length; j++) {
                if (isHighlight) {
                    Location hl = relativeHighlightLocation[i][j];
                    Location location = new Location(getX() + hl.getX(), getY() + hl.getY());
                    absoluteHighlightLocation[i][j] = location;
                }
                tetris.highlightLocations(absoluteHighlightLocation[i][j], isHighlight);
            }
        }
    }

    // Auto play
    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    private void autoMove() {
        String move = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);
        switch (move) {
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
        }
        autoBlockIndex++;
    }

    private boolean canAutoPlay() {
        return autoBlockMove != null
                && !autoBlockMove.isEmpty()
                && autoBlockMove.length() > autoBlockIndex;
    }

    // Movement
    void left() {
        if (isStarting) return;
        setDirection(180);
        advance();
    }

    void right() {
        if (isStarting) return;
        setDirection(0);
        advance();
    }

    void drop() {
        if (isStarting) return;
        tetris.speedup();
    }

    void display(GameGrid grid, Location location) {
        for (TetroBlock a : blocks) {
            Location loc = new Location(
                    location.x + a.getRelativeLocation(0).x,
                    location.y + a.getRelativeLocation(0).y);
            grid.addActor(a, loc);
        }
    }

    protected boolean advance() {
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
            if (a.isRemoved()) continue;
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

    // Overrides
    @Override
    public void setDirection(double dir) {
        super.setDirection(dir);
        for (TetroBlock a : blocks) {
            a.setDirection(dir);
        }
    }

    @Override
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

    @Override
    public void removeSelf() {
        super.removeSelf();
        for (TetroBlock a : blocks) {
            a.removeSelf();
        }
    }

    // Feature 2 setters
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
}
