import java.awt.*;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private boolean isFirstPlayer;
    private Color gamePieceColor;

    HumanPlayer(boolean isFirst, Color gamePieceColor) {
        this.isFirstPlayer = isFirst;
        this.gamePieceColor = gamePieceColor;
    }

    public Pair getMove(Pair positions[], int moves, Color opponentColor, Display display) {
        Scanner scanner =new Scanner(System.in);
        int xUser, yUser, scanIndex;
        scanIndex = 0;
        xUser = -5;
        yUser = -5;
        display.itsYourMove(this);
        display.printPossibleMoves(positions, moves);
        display.printString("Please enter your move row (space) col: ");
        while(scanner.hasNext()) {
            if (scanIndex == 0) {
                xUser = scanner.nextInt();
                scanIndex++;
            } else if (scanIndex == 1) {
                yUser = scanner.nextInt();
                scanIndex++;
            }
        }
//        if (cin.fail()) {
//            cin.clear();
//            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//            // illegal value in purpose
//            xUser = -5;
//            yUser = -5;
//        }
        Pair inputUser = new Pair(xUser, yUser);
        return inputUser;
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
}
