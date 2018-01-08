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
        int moves;
        while (!this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
            String move, moveB;
            Pair userInput;
            moves = 0;
            //new
            Pair pArr[] = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
            display.printBoard(this.gameLogic.getBoard());
            if (this.blackTurn) {
                if (this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
                    return;
                }
                moves = this.gameLogic.possibleMoves(pArr, moves, this.bHP.getColor());
                if (moves == 0) {
                    this.bHP.noMove(this.display);
                    noMoreActionsB = true;
                } else {
                    do {
                        userInput = bHP.getMove(pArr, moves, wHP.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves, display));
                    this.gameLogic.flipCell(userInput, wHP.getColor(), bHP.getColor());
                    //display.printPair(new Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionsB = false;
                }
                this.blackTurn = false;
            } else {
                moves = this.gameLogic.possibleMoves(pArr, moves, wHP.getColor());
                if (moves == 0) {
                    this.wHP.noMove(this.display);
                    noMoreActionW = true;
                } else {
                    do {
                        userInput = wHP.getMove(pArr, moves, bHP.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves, display));
                    this.gameLogic.flipCell(userInput, bHP.getColor(), wHP.getColor());
                    //display.printPair(new Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionW = false;
                }
                this.blackTurn = true;
            }
        }
        }
}
