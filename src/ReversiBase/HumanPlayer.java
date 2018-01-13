package ReversiBase;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {
    private boolean isFirstPlayer;
    private Color gamePieceColor;

    HumanPlayer(boolean isFirst, Color gamePieceColor) {
        this.isFirstPlayer = isFirst;
        this.gamePieceColor = gamePieceColor;
    }

    public Pair getMove(Pair positions[], int moves, Color opponentColor, Display display) {
        int xUser, yUser;
        xUser = -5;
        yUser = -5;

        display.itsYourMove(this);
        display.printPossibleMoves(positions, moves);
        display.printString("Please enter your move row (space) col: ");
        String point = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            point = reader.readLine();
            xUser = Character.getNumericValue(point.charAt(0));
            yUser = Character.getNumericValue(point.charAt(2));
        } catch (Exception e) {
        }
        return new Pair(xUser, yUser);
    }

    public boolean isStarter() {
        return this.isFirstPlayer;
    }

    public void noMove(Display display) {
        String anyKey;
        display.noPossiblePlayerMove(this);
//        getline(cin, anyKey);
//        do
//        {
//            cout << '\n' << "Press a key to continue...";
//        } while (cin.get() != '\n');
        // prints a new line
        display.printString("");
    }
    public Color getColor() {
        return this.gamePieceColor;
    }

    public void setColor(Color gamePieceColor) {
        this.gamePieceColor = gamePieceColor;
    }
}

