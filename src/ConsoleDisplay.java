import java.awt.*;

public class ConsoleDisplay implements Display {
    @Override
    public void printBoard(Board board) {
        int size = board.getSize();
        GamePiece[][] gamePieces;
        gamePieces = board.getGamePieces();
        Color startingColor = board.getStartingColor();
        Color notStartingColor = board.getNotStartingColor();
        int i, j;
        char symbol = ' ';
        System.out.print(" |") ;
        for (i = 0; i < size; i++) {
            System.out.print(" " + (i+1) + " |");
        }
        System.out.println(" ");
        System.out.print("--");
        for (i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println(" ");
        for (i = 0; i < size; i++) {
            System.out.print((i+1) + "|");
            for (j = 0; j < size; j++) {
                if (gamePieces[i][j].isEmpty()) {
                    symbol = ' ';
                }
                if (gamePieces[i][j].getColor() == notStartingColor) {
                    symbol = 'O';
                }
                if (gamePieces[i][j].getColor() == startingColor) {
                    symbol = 'X';
                }
                System.out.print(" " + symbol + " |");
            }
            System.out.println(" ");
            System.out.print("--");
            for (j = 0; j < size; j++) {
                System.out.print("----");
            }
            System.out.println(" ");
        }
    }

    @Override
    public void printPossibleMoves(Pair[] positions, int moves) {
        System.out.print("Your possible moves: ");
        for (int i = 0; i < moves; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            printPair(positions[i]);
        }
        System.out.println("");
        System.out.println("");
    }

    @Override
    public void printPair(Pair p) {
        System.out.print("(" + (p.getRow() + 1) + "," + (p.getCol() + 1) + ")");

    }

    @Override
    public void printString(String string) {
        System.out.println(string);
    }

    @Override
    public void itsYourMove(Player p) {
        if (!p.isStarter()) {
            System.out.println("Player2: It's your move.");
        } else {
            System.out.println("Player1: It's your move.");
        }
    }

    @Override
    public void noPossiblePlayerMove(Player p) {
        System.out.print("No possible moves for ");
        if (p.isStarter()) {
            System.out.println("X");
        } else {
            System.out.println("O");
        }
        this.printString("Play passes back to other player");
    }
}
