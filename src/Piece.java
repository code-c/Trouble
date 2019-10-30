import java.util.ArrayList;

/**
 * Piece class
 *
 * Represents a single game piece
 * Stores area, player num, offset, relativeLocation, board size, finish size
 * Supports moving to board, moving to home, moving n spaces, and getting area
 */

public class Piece {

    // fields
    private Area ar;
    private int playerNum;
    private int playerOffset;
    private int pieceNum;
    private int relativeLoc;

    /**
     * Constructor
     * @param playerNum, the player's turn value
     */
    Piece(int playerNum, int pieceNum) {
        ar = Area.HOME;
        this.playerNum = playerNum;
        this.pieceNum = pieceNum + 1;
        playerOffset = playerNum * Constants.OFFSET;
        this.toHome();
    }

    /**
     * Send piece to board and initialize at relative '0'
     */
    public void toBoard() {
        ar = Area.BOARD;
        relativeLoc = 0;
    }

    /**
     * Send piece back home and reset relativeLoc to -1
     */
    public void toHome() {
        ar = Area.HOME;
        relativeLoc = pieceNum;
    }


    public void setArLoc(AreaLoc arLoc) {
        this.ar = arLoc.ar;
        this.relativeLoc = arLoc.loc;
    }

    public ArrayList<AreaLoc> getValidMoves(int n) {
        ArrayList<AreaLoc> areaLocs = new ArrayList<>();

        // moving on board
        if (ar == Area.BOARD) {
            if (relativeLoc + n > Constants.BOARDSIZE) { // full lap
                if ((relativeLoc + n) - (Constants.BOARDSIZE) > Constants.FINISHSIZE) { // continuing around
                    areaLocs.add(new AreaLoc(Area.BOARD,(relativeLoc + n) % Constants.BOARDSIZE));
                } else {  // entering finish
                    areaLocs.add(new AreaLoc(Area.FINISH,(relativeLoc + n) - (Constants.BOARDSIZE)));
                    areaLocs.add(new AreaLoc(Area.BOARD,(relativeLoc + n) % Constants.BOARDSIZE));
                }

            } else {
                areaLocs.add(new AreaLoc(Area.BOARD,relativeLoc + n)); // still completing lap
            }

        } else if (ar == Area.FINISH) {  // moving in finish
            if (relativeLoc + n <= Constants.FINISHSIZE) {
                areaLocs.add(new AreaLoc(Area.FINISH,relativeLoc + n));
            }

        } else  {  // tried to move directly from HOME
            if (n == 6) {
                areaLocs.add(new AreaLoc(Area.BOARD,1));
            } else {
                areaLocs.add(new AreaLoc(Area.HOME, pieceNum));            }
        }

        return areaLocs;
    }

    /**
     * Area getter
     * @return current area
     */
    public Area getAr() {
        return ar;
    }

    /**
     * Computes the absolute location of the piece on the board using its relative location
     *
     * If the piece is in HOME or FINISH absoluteLoc = relativeLoc
     * @return the absolute location of the piece
     */
    public int getAbsoluteLoc() {
        if (ar == Area.BOARD) {
            return (relativeLoc + playerOffset) % (Constants.BOARDSIZE + 1);
        }
        return relativeLoc;
    }

    public int getPlayerNum() {
        return playerNum;
    }
    /**
     *
     * @return relativeLoc
     */
    public int getRelativeLoc() {
        return relativeLoc;
    }

    /**
     * Overridden toString
     */
    public String toString() {
        String s = "Area: " + ar + " Rel: " + relativeLoc  + " Abs: " + getAbsoluteLoc();;
        return s;
    }
}