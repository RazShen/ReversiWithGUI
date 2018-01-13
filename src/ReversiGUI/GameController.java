package ReversiGUI;

import ReversiBase.Board;
import ReversiBase.GameLogic;
import ReversiBase.RegularGameLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Board board;

    @FXML
    private HBox root;

    @FXML
    private Label message;

    @FXML
    private Label currentPlayer;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SettingsParser parser = new SettingsParser();
        parser.parseSettingsFile();
        int size = parser.getBoardSize();
        String player1Color, player2Color;
        String startingPlayer = parser.getStartingPlayer();
        if (startingPlayer.equals("player1")) {
            player1Color = parser.getPlayer1Color();
            player2Color = parser.getPlayer2Color();
        } else {
            player1Color = parser.getPlayer2Color();
            player2Color = parser.getPlayer1Color();
        }
        this.board = new Board(size, Color.web(player1Color), Color.web(player2Color));
        GuiBoard guiBoard = new GuiBoard(board);
        root.getChildren().add(0, guiBoard);
        root.setAlignment(Pos.TOP_LEFT);
        guiBoard.draw();

        currentPlayer = new Label("Current player: First");
        player1Score = new Label("First player's score: 2");
        player2Score = new Label("Second player's score: 2");
        message = new Label("Player 1: It's your move!");
        message.setFont(new Font(30));


    }

    @FXML
    protected void startGame() {

    }
}
