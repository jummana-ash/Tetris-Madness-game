package tetris;

/**
 * The enum to repsent different block pieces and have different attributes for each block piece
 */
public enum BlockPieces {
    I(0, "I"), J(1, "J"), L(2, "L"), O(3, "O"), S(4, "S"), T(5, "T"), Z(6, "Z");

    private int blockIndex = -1;
    private String blockName = "";

    BlockPieces(int blockIndex, String blockName) {
        this.blockIndex = blockIndex;
        this.blockName = blockName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public String getBlockName() {
        return blockName;
    }

    public static BlockPieces getBlockPiece(String blockName) {
        for (BlockPieces blockPiece : BlockPieces.values()) {
            if (blockPiece.blockName.equals(blockName)) {
                return blockPiece;
            }
        }
        return BlockPieces.I;
    }

    @Override
    public String toString() {
        return blockName;
    }
}
