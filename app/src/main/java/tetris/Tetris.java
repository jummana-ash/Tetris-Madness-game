package tetris;// Tetris.java

import ch.aplu.jgamegrid.*;
import tetris.utility.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;

// test commit
public class Tetris extends JFrame implements GGActListener {
    public static final String statisticsFilePath = "statistics.txt";
    private Actor currentBlock = null;  // Currently active block
    private Actor blockPreview = null;   // block in preview window
    private int score = 0;
    private Random random = new Random(0);

    private boolean feature1Active;
    private boolean feature2Active;
    private SpawnStrategy spawnStrategy;
    private Statistics statistics;


    private boolean isAuto = false;

    private int MANUAL_SIMULATION_PERIOD = 300;
    private int MANUAL_DROP_SIMULATION_PERIOD = 50;
    private int AUTO_SIMULATION_PERIOD = 50;

    // For testing mode, the block will be moved automatically based on the blockActions.
    // L is for Left, R is for Right, T is for turning (rotating), and D for down
    private String[] blockActions = null;
    private int blockActionIndex = 0;

    private String[] blockPieces = null;
    private int blockPieceIndex = 0;
    Logger logger = new Logger();

    /**
     * Initialise object
      */
    private void initWithProperties(Properties properties) {
        random = new Random(30006);
        isAuto = Boolean.parseBoolean(properties.getProperty("isAuto"));
        String blockActionProperty = properties.getProperty("actions", "");
        blockActions = blockActionProperty.split(",");

        String blockPieceProperty = properties.getProperty("pieces", "");
        blockPieces = blockPieceProperty.split(",");

        feature1Active = "active".equals(properties.getProperty("features.1"));
        feature2Active = "active".equals(properties.getProperty("features.2"));

        if (feature2Active) {
            spawnStrategy = new RandomSpawnStrategy(properties);
        } else {
            spawnStrategy = new CentreSpawnStrategy();
        }

        statistics = new Statistics(statisticsFilePath);
    }

    public Tetris(Properties properties) {
        // Initialise value
        initWithProperties(properties);
        blockActionIndex = 0;

        // Set up the UI components. No need to modify the UI Components
        tetrisComponents = new TetrisComponents();
        tetrisComponents.initComponents(this);
        gameGrid1.addActListener(this);

        gameGrid1.setSimulationPeriod(defaultSimulationPeriod());

        // Add the first block to start
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, ((TetrisPiece) currentBlock).getSpawnLocation());
        gameGrid1.doRun();

        // Do not lose keyboard focus when clicking this window
        gameGrid2.setFocusable(false);
        setTitle("SWEN30006 Tetris Madness");
        score = 0;
        showScore(score);
    }


    /**
     * The game is called in a run loop, this method sleeps for 500 milliseconds
     * and only check if the game is over after 500 milliseconds
     */
    public String runApp() {
        setVisible(true);
        while (gameGrid1.isRunning()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return logger.getAllLog();
    }

    /**
     * Get the next block. If there is a value in the properties file,
     *  getting the next available block in the list, otherwise getting a random block
     * @return BlockPieces
     */
    private BlockPieces getNextBlock() {
        if (blockPieceIndex < blockPieces.length) {
            BlockPieces piece = BlockPieces.getBlockPiece(blockPieces[blockPieceIndex++]);
            return piece;
        }

        // feature 1 off - only 7 options
        // feature 1 on - 10 options
        int rnd = feature1Active ? random.nextInt(10) : random.nextInt(7);
        return BlockPieces.values()[rnd];
    }

    /* automates instantialising testris blocks */

    private TetrisPiece buildPiece(BlockPieces piece) {
        return switch (piece) {
            case I -> new I(this);
            case J -> new J(this);
            case L -> new L(this);
            case O -> new O(this);
            case S -> new S(this);
            case T -> new T(this);
            case Z -> new Z(this);
            case CROSS -> new Cross(this);
            case PLUS -> new Plus(this);
            case SLASH -> new Slash(this);
        };
    }

    /**
     * create a block and assign to a preview mode
      */
    Actor createRandomTetrisBlock() {
        if (blockPreview != null)
            blockPreview.removeSelf();

        // If the game is in auto test mode, then the block will be moved according to the blockActions
        String currentBlockMove = "";
        if (blockActions.length > blockActionIndex) {
            currentBlockMove = blockActions[blockActionIndex];
        }

        blockActionIndex++;

        BlockPieces randomBlockPiece = getNextBlock();
        /* debug
        System.out.println("CREATING PIECE: " + randomBlockPiece.getBlockName());*/
        TetrisPiece t = buildPiece(randomBlockPiece);
        TetrisPiece preview = buildPiece(randomBlockPiece);
        if (isAuto) t.setAutoBlockMove(currentBlockMove);
        preview.display(gameGrid2, new Location(2, 1));
        blockPreview = preview;

        SpawnConfig config = spawnStrategy.getSpawnConfig();

        // To Do : safety bounds

        int safeX = Math.min(config.spawnLocation.x, 11);
        Location safeLocation = new Location(safeX, config.spawnLocation.y);


        ((TetrisPiece) t).setSpawnLocation(safeLocation);
        ((TetrisPiece) t).setSpeed(config.fallSpeed);

        // record block for statistics
        statistics.recordPiece(randomBlockPiece.getBlockName());

        return t;
    }

    /**
     * highlight for a specific location. Check and use the appropriate color
     * @param location
     * @param highlight
     */
    void highlightLocations(Location location, boolean highlight) {
        if (highlight) {
            gameGrid1.getBg().fillCell(location, Color.YELLOW);
        } else {
            gameGrid1.setGridColor(new java.awt.Color(255, 3, 0));
        }
    }

    private int defaultSimulationPeriod() {
        return isAuto ? AUTO_SIMULATION_PERIOD : MANUAL_SIMULATION_PERIOD;
    }

    public void moveToNextTetris(Actor t) {
        currentBlock = t;
        gameGrid1.setSimulationPeriod(defaultSimulationPeriod());
    }

    public void speedup() {
        gameGrid1.setSimulationPeriod(MANUAL_DROP_SIMULATION_PERIOD);
    }

    /**
     * Check if a specific location is within the game grid
     * @param location
     * @return
     */
    public boolean isInsideBoundary(Location location) {
        if (location.getX() < 0) {
            return false;
        }

        if (location.getX() >= gameGrid1.getNbHorzCells()) {
            return false;
        }
        return true;
    }

    /**
     * Handle user input to move block. Arrow left to move left,
     * Arrow right to move right, Arrow up to rotate and
     * Arrow down for going down
      */
    private void moveBlock(int keyEvent) {
        if (!(currentBlock instanceof TetrisPiece)) return;
        TetrisPiece piece = (TetrisPiece) currentBlock;
        switch (keyEvent) {
            case KeyEvent.VK_UP:    piece.rotate(); break;
            case KeyEvent.VK_LEFT:  piece.left();   break;
            case KeyEvent.VK_RIGHT: piece.right();  break;
            case KeyEvent.VK_DOWN:  piece.drop();   break;
            default: return;
        }
    }

    /**
     * The game is called in a run loop, this method for a tetris is called every
     * 1/30 seconds as the starting point
     */
    public void act() {
        removeFilledLine();
        moveBlock(gameGrid1.getKeyCode());
        logger.logEvent(currentBlock.toString());
    }

    /**
     * Check if a line is completely filled and clear the line and update the score
     */
    private void removeFilledLine() {
        for (int y = 0; y < gameGrid1.nbVertCells; y++) {
            boolean isLineComplete = true;
            TetroBlock[] blocks = new TetroBlock[gameGrid1.nbHorzCells];   // One line
            // Calculate if a line is complete
            for (int x = 0; x < gameGrid1.nbHorzCells; x++) {
                blocks[x] =
                        (TetroBlock) gameGrid1.getOneActorAt(new Location(x, y), TetroBlock.class);
                if (blocks[x] == null) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                // If a line is complete, we remove the component block of the shape that belongs to that line
                for (int x = 0; x < gameGrid1.nbHorzCells; x++)
                    gameGrid1.removeActor(blocks[x]);
                ArrayList<Actor> allBlocks = gameGrid1.getActors(TetroBlock.class);
                for (Actor a : allBlocks) {
                    int z = a.getY();
                    if (z < y)
                        a.setY(z + 1);
                }
                gameGrid1.refresh();
                score++;
                showScore(score);
                logger.logEvent("Score: " + score);
            }
        }
    }

    /**
     * Show Score
      */
    private void showScore(final int score) {
        scoreText.setText(score + " points");
    }

    /**
     * Display the game over
     */
    void gameOver() {
        // record stats at end of round
        statistics.endRound(score);

        gameGrid1.addActor(new Actor("sprites/gameover.gif"), new Location(5, 5));
        gameGrid1.doPause();
        if (isAuto) {
            gameGrid1.doPause();
        }

    }

    /**
     * Start a new game
      */
    public void startBtnActionPerformed(java.awt.event.ActionEvent evt) {
        gameGrid1.doPause();
        gameGrid1.removeAllActors();
        gameGrid2.removeAllActors();
        gameGrid1.refresh();
        gameGrid2.refresh();
        gameGrid2.delay(getDelayTime());
        blockActionIndex = 0;
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();
        gameGrid1.requestFocus();
        score = 0;
        showScore(score);

        // record stats at the start of a new game
        statistics = new Statistics(statisticsFilePath);
    }

    private int getDelayTime() {
        if (isAuto) {
            return 500;
        } else {
            return 2000;
        }
    }

    // AUTO GENERATED - do not modify//GEN-BEGIN:variables
    public ch.aplu.jgamegrid.GameGrid gameGrid1;
    public ch.aplu.jgamegrid.GameGrid gameGrid2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField scoreText;
    public javax.swing.JButton startBtn;
    private TetrisComponents tetrisComponents;
    // End of variables declaration//GEN-END:variables

}
