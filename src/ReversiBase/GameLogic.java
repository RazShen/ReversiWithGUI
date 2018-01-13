package ReversiBase;


import javafx.scene.paint.Color;

public abstract class GameLogic {
    protected Board board;
    protected Color startingColor, notStartingColor;

    enum ScanDirection {
        NorthWest, North, NorthEast, West, East, SouthWest, South, SouthEast
    };
    enum GameWinner {
        Draw, BlackWon, WhiteWon
    };

    public GameLogic() {};

    public GameLogic(Board board, Color startingColor, Color notStartingColor) {
        this.board = board;
        this.notStartingColor = notStartingColor;
        this.startingColor = startingColor;
    }

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

    public int getBoardSize() {
        return this.board.getSize();
    }
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

    public Board getBoard() {
        return this.board;
    }

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

    abstract public boolean validMove(Pair p, ScanDirection scanD, Color opponentP, Color player);

    abstract public int possibleMoves(Pair pairArr[], int index, Color player);

    abstract public boolean checkCell(Pair p, Color opponentP, Color player);

    abstract public void flipCell(Pair p, Color opponentP, Color player);

    abstract public boolean checkInput(Pair p, Pair arr[], int count);
}
