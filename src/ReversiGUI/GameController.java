package ReversiGUI;

import ReversiBase.Board;
import ReversiBase.GameLogic;
import ReversiBase.Pair;
import ReversiBase.RegularGameLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.xml.stream.Location;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    private Board board;
    private GameLogic gameLogic;
    @FXML
    private GuiBoard guiBoard;
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
        this.guiBoard = new GuiBoard(board);
        root.getChildren().add(0, guiBoard);
        root.setAlignment(Pos.TOP_LEFT);
        guiBoard.draw();
        VBox gameStatus = new VBox();
        currentPlayer = new Label("\nCurrent player: First");
        player1Score = new Label("First player score: 2");
        player2Score = new Label("Second player score: 2");
        message = new Label("Player 1:\nIt's your move!");
        message.setFont(new Font(15));
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(20);
        gameStatus.setSpacing(10);
        gameStatus.getChildren().addAll(currentPlayer, player1Score, player2Score, message);
        root.getChildren().add(gameStatus);
        this.startGame();



    }

    @FXML
    protected void startGame() {
        guiBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Pair move = convertClickToPair(e.getX(), e.getY());
            //performMove(move);
        });
    }

    private Pair convertClickToPair(double x, double y) {
        int cellSize = this.guiBoard.getCellSize();
        for (int i = 0; i < this.board.getSize() ; i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (x >= i*cellSize && x <= (i+1)* cellSize) {
                    if (y >= j*cellSize && y <= (j+1) * cellSize) {
                        System.out.println((i+1) + "," + (j+1));
                        return new Pair(i+ 1, j+ 1);
                    }
                }
            }
        }
        return new Pair(-1, -1);
    }

    private boolean checkInput(Pair input) {
       // this.gameLogic.validMove()
        return false;
    }
}
