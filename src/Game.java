public class Game {

    private boolean isFirst;
    private Player player1;
    private Player player2;
    private Display display;
    private GameLogic gameLogic;

    public Game(GameLogic gameLogic, Display display, Menu menu) {
        this.gameLogic = gameLogic;
        this.display = display;

    }

    public void run() {
        boolean noActionsPlayer1 = false;
        boolean noActionsPLayer2 = false;

    }
}
