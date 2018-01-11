package ReversiBase;

import java.awt.*;

public class Board {
    private GamePiece[][] gamePieces;
    private int size;
    private Color startingColor;
    private Color notStartingColor;

    public Board(int size, Color startingColor, Color notStartingColor) {
        this.size = size;
        gamePieces = new GamePiece[size][size];
        this.startingColor = startingColor;
        this.notStartingColor = notStartingColor;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.gamePieces[i][j] = new GamePiece();
            }
        }
        this.gamePieces[this.size / 2 - 1][this.size / 2 - 1].setColor(notStartingColor);
        this.gamePieces[this.size / 2][this.size / 2].setColor(notStartingColor);
        this.gamePieces[this.size / 2 - 1][this.size / 2].setColor(startingColor);
        this.gamePieces[this.size / 2][this.size / 2 - 1].setColor(startingColor);
    }

    public GamePiece getCellStatus(Pair p) {
        if (p.getRow() >= 0 && p.getRow() < this.size && p.getCol() >= 0 && p.getCol() < this.size) {
            return this.gamePieces[p.getRow()][p.getCol()];
        }
        GamePiece emptyGamePiece = new GamePiece();
        return emptyGamePiece;
    }

    public void changeStatus(Pair p, Color c) {
        if (p.getRow() >= 0 && p.getRow() < this.size && p.getCol() >= 0 && p.getCol() < this.size) {
            this.gamePieces[p.getRow()][p.getCol()].setColor(c);
        }
    }

    public GamePiece[][] getGamePieces() {
        return this.gamePieces;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (!this.gamePieces[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSize() {
        return this.size;
    }

    public Color getStartingColor() {
        return this.startingColor;
    }

    public Color getNotStartingColor() {
        return this.notStartingColor;
    }

}


