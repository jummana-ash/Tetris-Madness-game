package tetris;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Statistics {
    // piece order IJLOSTZX
    private static final String[] PIECE_ORDER = {"I","J","L","O","S","T","Z","X","+","/"};

    private int roundNumber = 0;
    private Map<String, Integer> pieceCounts = new LinkedHashMap<>();
    private String filePath;
    private boolean isFirstRound = true;

    public Statistics(String filePath) {
        this.filePath = filePath;
        resetRound();
    }

    // RECORD the pieces being spawned
    public void recordPiece(String pieceName) {
        pieceCounts.merge(pieceName, 1, Integer::sum);
    }

    // game over - end of round
    public void endRound(int score) {
        roundNumber++;
        writeRoundToFile(score);
        resetRound();
    }

    private void writeRoundToFile(int score) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, !isFirstRound))) {
            pw.println("Round #" + roundNumber);
            pw.println("Score: " + score);
            for (String piece : PIECE_ORDER) {
                pw.println(piece + ": " + pieceCounts.getOrDefault(piece, 0));
            }
            pw.println("-----");
            isFirstRound = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void resetRound() {
        pieceCounts.clear();
        for (String piece : PIECE_ORDER) {
            pieceCounts.put(piece, 0);
        }
    }
}

