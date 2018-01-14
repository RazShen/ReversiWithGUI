package ReversiBase;


import javafx.scene.paint.Color;

public abstract class GameLogic {
    protected Board board;
    protected Color startingColor, notStartingColor;
    /**
     * Enum for the board scanning directions.
     */
    enum ScanDirection {
        NorthWest, North, NorthEast, West, East, SouthWest, South, SouthEast
    };
    /**
     * Enum for game winning.
     */
    enum GameWinner {
        Draw, BlackWon, WhiteWon
    };

    public GameLogic() {};
    /**
     * This constructor creates a basic game logic abstract class.
     * @param board inputted board.
     * @param startingColor inputted startingColor.
     * @param notStartingColor inputted notStartingColor.

     */
    public GameLogic(Board board, Color startingColor, Color notStartingColor) {
        this.board = board;
        this.notStartingColor = notStartingColor;
        this.startingColor = startingColor;
    }
    /**
     * This method returns who won the game, or draw by scanning the board cells.
     * @return who won the game, or draw
     */
    public GameWinner whoWon() {
        int firstPlayerCells = 0;
        int secondPlayerCells = 0;
        int size = this.getBoardSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board.getCellStatus(new Pair(i, j)).getColor().toString().equals(notStartingColor.toString()) ) {
                    secondPlayerCells++;
                } else if (this.board.getCellStatus(new Pair(i, j)).getColor().toString().equals(startingColor.toString()) ) {
                    firstPlayerCells++;
                }
            }
        }
        if (firstPlayerCells > secondPlayerCells) {
            return GameWinner.BlackWon;
        } else if (secondPlayerCells > firstPlayerCells) {
            return GameWinner.WhiteWon;
        } else {
            return GameWinner.Draw;
        }
    }
    /**
     * This method returns the size of the board.
     * @return
     */
    public int getBoardSize() {
        return this.board.getSize();
    }
    /**
     * This method checks if the game should end.
     * @param noMoreActionsB boolean for the black player possible move (have moves or don't have moves).
     * @param noMoreActionsW boolean for the while player possible move (have moves or don't have moves).
     * @return
     */
    public boolean checkAndAnnounceFinish(boolean noMoreActionsB, boolean noMoreActionsW, Display display) {
        if (this.board.isBoardFull()) {
            display.printString("Current board:");
            display.printBoard(board);
            display.printString("The board is full");
            if (this.whoWon() == GameWinner.Draw) {
                display.printString("It's a draw");
            } else if (this.whoWon() == GameWinner.BlackWon) {
                display.printString(this.startingColor.toString() + " player wins");
            } else {
                display.printString(this.notStartingColor.toString() + " player wins");
            }
            return true;
        }
        if (noMoreActionsB && noMoreActionsW) {
            display.printString("No more moves available for both players: ");
            if (this.whoWon() ==  GameWinner.Draw) {
                display.printString("It's a draw");
            } else if (this.whoWon() == GameWinner.BlackWon) {
                display.printString(this.startingColor.toString() + " player wins");
            } else {
                display.printString(this.notStartingColor.toString() + " player wins");
            }
            return true;
        }
        return false;
    }
    /**
     * This method returns the current board (used for cloning).
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * thic method gets first player advantage
     * @return return the first player advantage
     */
    public int getFirstPlayerAdvantage() {
        int firstPlayerCells = 0;
        int secondPlayerCells = 0;
        int boardSize = this.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.board.getCellStatus(new Pair(i, j)).getColor().toString()
                        .equals(this.notStartingColor.toString())) {
                    secondPlayerCells++;
                } else if (this.board.getCellStatus(new Pair(i, j))
                        .getColor().toString().equals(this.startingColor.toString())) {
                    firstPlayerCells++;
                }
            }
        }
        return (firstPlayerCells - secondPlayerCells);
    }
    /**
     * thic method gets first player score
     * @return return the first player score
     */
    public int getFirstPlayerScore() {
        int firstPlayerCells = 0;
        int boardSize = this.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                 if (this.board.getCellStatus(new Pair(i, j)).getColor().toString()
                         .equals(this.startingColor.toString()) ) {
                    firstPlayerCells++;
                }
            }
        }
        return firstPlayerCells;
    }
    /**
     * thic method gets second player score
     * @return return the second player score
     */
    public int getSecondPlayerScore() {
        int secondPlayerCells = 0;
        int boardSize = this.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.board.getCellStatus(new Pair(i, j)).getColor()
                        .toString().equals(this.notStartingColor.toString()) ) {
                    secondPlayerCells++;
                }
            }
        }
        return secondPlayerCells;
    }
    /**
     * Pure virtual function to check if the move is valid.
     * @param p wanted cell.
     * @param scanD scan direction.
     * @param opponentP opponent's player color.
     * @param player: player's color.
     * @return true/false.
     */
    abstract public boolean validMove(Pair p, ScanDirection scanD, Color opponentP, Color player);
    /**
     * Pure virtual function to update all the possible moves.
     * @param pairArr inputted array to update.
     * @param index number of moves.
     * @param player player's color.
     */
    abstract public int possibleMoves(Pair pairArr[], int index, Color player);
    /**
     * Pure virtual function to check if the cell is a possible move.
     * @param p inputted pair.
     * @param opponentP opponent's player color.
     * @param player: player's color.
     * @return true/false.
     */
    abstract public boolean checkCell(Pair p, Color opponentP, Color player);
    /**
     * Pure virtual function to flip the right cells after the user played it's turn.
     * @param p inputted pair (wanted move).
     * @param opponentP opponent's player color.
     * @param player: player's color.
     */
    abstract public void flipCell(Pair p, Color opponentP, Color player);
    /**
     * Virtual method to checks the users input validation (right format & picking a move from the possible moves)
     * @param p wanted users move.
     * @param arr array of possible moves.
     * @param count number of possible moves.
     * @return true/false for good/bad format.
     */
    abstract public boolean checkInput(Pair p, Pair arr[], int count);
}
