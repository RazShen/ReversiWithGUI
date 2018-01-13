package ReversiBase;

import ReversiGUI.*;
import javafx.scene.paint.Color;

public class main {
    public static void consoleMain(String[] args) {
        Game game;
        Display display = new ConsoleDisplay();
        Board board = new Board(8, Color.BLACK, Color.WHITE);
        GameLogic gameLogic = new RegularGameLogic(board, Color.BLACK, Color.WHITE);
        game = new Game(gameLogic, display, Color.BLACK, Color.WHITE);
//                    Color.web(settingsParser.getPlayer1Color()));
//        if (settingsParser.getStartingPlayer().equals("player1")) {
//            RegularGameLogic gameLogic = new RegularGameLogic(settingsParser.getBoardSize(),
//                    Color.web(settingsParser.getPlayer1Color()), Color.web(settingsParser.getPlayer2Color()));
//            game = new Game(gameLogic, display, Color.web(settingsParser.getPlayer1Color()),
//                    Color.web(settingsParser.getPlayer2Color()));
//        } else {
//            RegularGameLogic gameLogic = new RegularGameLogic(settingsParser.getBoardSize(),
//                    Color.web(settingsParser.getPlayer2Color()), Color.web(settingsParser.getPlayer1Color()));
//            game = new Game(gameLogic, display, Color.web(settingsParser.getPlayer2Color()),
//                    Color.web(settingsParser.getPlayer1Color()));
//        }
        game.run();

    }
}
