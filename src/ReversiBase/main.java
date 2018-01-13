package ReversiBase;

import ReversiGUI.*;
import javafx.scene.paint.Color;

public class main {
    public static void regGame(String[] args) {
        Game game;
        Display display = new ConsoleDisplay();

        SettingsParser settingsParser = new SettingsParser();
        settingsParser.parseSettingsFile();

        if (settingsParser.getStartingPlayer().equals("player1")) {
            RegularGameLogic gameLogic = new RegularGameLogic(settingsParser.getBoardSize(),
                    Color.web(settingsParser.getPlayer1Color()), Color.web(settingsParser.getPlayer2Color()));
            game = new Game(gameLogic, display, Color.web(settingsParser.getPlayer1Color()),
                    Color.web(settingsParser.getPlayer2Color()));
        } else {
            RegularGameLogic gameLogic = new RegularGameLogic(settingsParser.getBoardSize(),
                    Color.web(settingsParser.getPlayer2Color()), Color.web(settingsParser.getPlayer1Color()));
            game = new Game(gameLogic, display, Color.web(settingsParser.getPlayer2Color()),
                    Color.web(settingsParser.getPlayer1Color()));
        }
        game.run();

    }
}
