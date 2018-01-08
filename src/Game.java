import java.awt.*;

public class Game {

    private boolean  blackTurn;;
    private Player bHP;
    private Player wHP;
    private Display display;
    private GameLogic gameLogic;

    public Game(GameLogic gameLogic, Display display, Color player1Color, Color player2Color) {
        this.gameLogic = gameLogic;
        this.display = display;
        this.blackTurn = true;
        this.bHP = new HumanPlayer(true, player1Color);
        this.wHP = new HumanPlayer(false, player2Color);

    }

    public void run() {
        boolean noMoreActionsB = false;
        boolean noMoreActionW = false;
        while (!this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
            String move, moveB;
            Pair userInput;
            int moves = 0;
            //new
            Pair pArr[] = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
       //     cout << "Current board:" << endl << endl;
            display.printBoard(this.gameLogic.getBoard());
            if (this.blackTurn) {
                if (this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
                    return;
                }
                this.gameLogic.possibleMoves(pArr, moves, (HumanPlayer)bHP.);
                if (moves == 0) {
                    this.bHP.noMove(this.display);
                    noMoreActionsB = true;
                } else {
                    do {
                        userInput = bHP.getMove(pArr, moves, wHP.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves, display));
                    this.gameLogic.flipCell(userInput, wHP.getColor(), bHP.getColor());
                    // updating the server after the move according to the type
               //     display.xPlayed();
                    display.printPair(new Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionsB = false;
                }
                this.blackTurn = false;
            }
                this.gameLogic.possibleMoves(pArr, moves, wHP.getColor());
                if (moves == 0) {
                    this.wHP.noMove(this.display);
                    noMoreActionW = true;
                } else {
                    do {
                        userInput = wHP.getMove(pArr, moves, bHP.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves, display));
                    // updating the server after the move according to the type
                    this.gameLogic.flipCell(userInput, bHP.getColor(), wHP.getColor());
                    display.printPair(new Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionW = false;
                }
                this.blackTurn = true;
            }
        }

    }

}
