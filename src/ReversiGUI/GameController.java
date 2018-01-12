package ReversiGUI;

import ReversiBase.Board;
import ReversiBase.GameLogic;
import ReversiBase.RegularGameLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    private Board board;

    @FXML
    private HBox root;

    @FXML
    private Button settingsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SettingsParser parser = new SettingsParser();
        parser.parseSettingsFile();
        int size = parser.getBoardSize();
        String startingPlayer = parser.getStartingPlayer();
        if (startingPlayer.equals("player1")) {
            String player1Color = parser.getPlayer1Color();
            String player2Color = parser.getPlayer2Color();
        } else {
            String player1Color = parser.getPlayer2Color();
            String player2Color = parser.getPlayer1Color();
        }



    }

    @FXML
    protected void startGame() {

    }
}
