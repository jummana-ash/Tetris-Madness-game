package tetris;

import ch.aplu.jgamegrid.Actor;

/**
 * Please do not change this class. This class is used for testing and your code needs to print the correct output to pass the test
 */
public class TetrisGameCallback {
    private boolean isLoggingTest = false;

    public TetrisGameCallback() {
        this.isLoggingTest = isLoggingTest;
    }

    public void changeOfBlock(Actor oldBlock) {
        if (!isLoggingTest) {
            return;
        }
        if (oldBlock != null) {
            System.out.println(oldBlock);
        }
    }

    public void changeOfScore(int newScore) {
        if (!isLoggingTest) {
            return;
        }
        System.out.println("For testing, do not change. Score: " + newScore);
    }
}
