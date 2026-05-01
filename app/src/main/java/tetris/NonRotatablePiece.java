package tetris;

public abstract class NonRotatablePiece extends TetrisPiece {

    @Override
    public void rotate() {
        // pieces cannot rotate do nothing
    }
}
