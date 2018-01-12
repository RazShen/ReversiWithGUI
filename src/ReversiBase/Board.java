package ReversiBase;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Board extends GridPane{
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

//    public void draw() {
//        this.getChildren().clear();
//        for (int i = 1; i <= size; i++) {
//            for (int j = 1; j <= size; j++) {
//                Rectangle rectangle = new Rectangle(600 / size,600 / size);
//                rectangle.setStroke(Color.BLACK);
//                rectangle.setFill(Color.rgb(37,109,140));
//                StackPane cell = new StackPane();
//                cell.getChildren().add(rectangle);
//                this.add(cell, i , j);
//                if (this.gamePieces[i][j].getColor().toString().equals(this.startingColor.toString())) {
//                    this.add(new Circle(cell.getWidth()/2, cell.getHeight()/2,
//                            300/size ,this.startingColor), i, j);
//                } else if (this.gamePieces[i][j].toString().equals(this.notStartingColor.toString())) {
//                    this.add(new Circle(cell.getWidth()/2, cell.getHeight()/2,
//                            300/size ,this.notStartingColor), i, j);
//                } else {
//                    for (int k = 0; k < possibleMoves.size(); k++) {
//                        if (possibleMoves.get(k).getRow() == table[i][j].getSpot().getRow()
//                                && possibleMoves.get(k).getCol() == table[i][j].getSpot().getCol()) {
//                            this.add(new Circle(cell.getWidth()/2, cell.getHeight()/2,
//                                    100/size, possibleColor), i, j);
//                        }
//                    }
//                }
//            }
//        }




    }


