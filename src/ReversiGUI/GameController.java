package ReversiGUI;

import ReversiBase.Board;
import ReversiBase.GameLogic;
import ReversiBase.Pair;
import ReversiBase.RegularGameLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Board board;
    private GameLogic gameLogic;
    private boolean isPlayer1;
    private String player1Color;
    private String player2Color;
    private boolean noMoreActionsP1;
    private boolean noMoreActionsP2;
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

    @FXML
    private Label extraMessage;

    @FXML
    private Button quit;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.noMoreActionsP1 = false;
        this.noMoreActionsP2 = false;
        SettingsParser parser = new SettingsParser();
        parser.parseSettingsFile();
        int size = parser.getBoardSize();
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
        currentPlayer = new Label("\nCurrent player: Player 1");
        player1Score = new Label("First player score: 2");
        player2Score = new Label("Second player score: 2");
        message = new Label("Player 1:\nIt's your move!");
        extraMessage = new Label ("");
        extraMessage.setFont(new Font(15));
        message.setFont(new Font(15));
        this.quit = new Button("Quit");
        this.quit.setOnAction(ev -> {
            loadFXML("MenuControllerFXML.fxml", 650, 600, ev);
        });
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(20);
        gameStatus.setSpacing(10);

        gameStatus.getChildren().addAll(currentPlayer, player1Score, player2Score, message, extraMessage, quit);
        root.getChildren().add(gameStatus);
        this.isPlayer1 = true;
        this.gameLogic = new RegularGameLogic(this.board, Color.web(this.player1Color), Color.web(this.player2Color));
        guiBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Pair move = convertClickToPair(e.getX(), e.getY());
            this.singleMove(move);
        });
    }

    @FXML
    protected void singleMove(Pair move) {
        int firstScore = gameLogic.getFirstPlayerScore();
        int secondScore = gameLogic.getSecondPlayerScore();

        if (this.board.isBoardFull() || (this.noMoreActionsP2 && this.noMoreActionsP1)) {
            if (firstScore > secondScore) {
                message.setText("Game Over\nFirst player wins!");
            } else if (secondScore > firstScore) {
                message.setText("Game Over\nSecond player wins!");
            } else {
                message.setText("Game Over\nIt's a Tie!");
            }
        }


        int moves = 0;
        Pair pArr[] = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
        if (this.isPlayer1) {
            moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player1Color));
        } else {
            moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player2Color));
        }


        if (moves == 0) {
            if (this.isPlayer1) {
                message.setText("Player 1:\nYou have no more moves!");
                this.isPlayer1 = false;
            } else {
                message.setText("Player 2:\nYou have no more moves!");
                this.isPlayer1 = true;
            }
            if (this.isPlayer1) {
                this.noMoreActionsP1 = true;
            } else {
                this.noMoreActionsP2 = true;
            }
        }

        boolean validMove = this.gameLogic.checkInput(move, pArr, moves);

        if (validMove) {
            if(this.isPlayer1) {
                this.gameLogic.flipCell(move, Color.web(this.player2Color), Color.web(this.player1Color));
                noMoreActionsP1 = false;
                this.isPlayer1 = false;

//                pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
//                moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player2Color));
//                if(moves == 0) {
//                    this.isPlayer1 = true;
//                    this.extraMessage.setText("No moves for:\nPlayer 2");
//                }
            } else {
                this.gameLogic.flipCell(move, Color.web(this.player1Color), Color.web(this.player2Color));
                noMoreActionsP2 = false;
                this.isPlayer1 = true;

//                pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
//                moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player1Color));
//                if(moves == 0) {
//                    this.isPlayer1 = false;
//                    this.extraMessage.setText("No moves for:\nPlayer 1");
//                }

            }
            if (!isPlayer1) {
                currentPlayer.setText("\nCurrent Player: Player 2");
                message.setText("Player 2:\nIt's your move!");
            } else {
                currentPlayer.setText("\nCurrent Player: Player 1");
                message.setText("Player 1:\nIt's your move!");
            }
//            board.setPossibleMoves(logic.getPossibleMoves());
            guiBoard.setBoard(this.board);
            guiBoard.draw();
            firstScore = gameLogic.getFirstPlayerScore();
            secondScore = gameLogic.getSecondPlayerScore();
            player1Score.setText("Player 1 score: " + firstScore);
            player2Score.setText("Player 2 score: " + secondScore);

        }
        else {
            if (this.board.isBoardFull() || (this.noMoreActionsP2 && this.noMoreActionsP1)) {
                if (firstScore > secondScore) {
                    message.setText("Game Over\nFirst player wins!");
                } else if (secondScore > firstScore) {
                    message.setText("Game Over\nSecond player wins!");
                } else {
                    message.setText("Game Over\nIt's a Tie!");
                }
            } else if (moves == 0) {
                if (this.isPlayer1) {
                    message.setText("Player 1:\nYou have\nno more moves!");
                    this.isPlayer1 = false;
                } else {
                    message.setText("Player 2:\nYou have\nno more moves!");
                    this.isPlayer1 = true;
                }
                if (this.isPlayer1) {
                    this.noMoreActionsP1 = true;
                } else {
                    this.noMoreActionsP2 = true;
                }
            } else {
                message.setText("That not a valid move");
            }
        }
    }



    private Pair convertClickToPair(double x, double y) {
        int cellSize = this.guiBoard.getCellSize();
        for (int i = 0; i < this.board.getSize() ; i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (x >= i*cellSize && x <= (i+1)* cellSize) {
                    if (y >= j*cellSize && y <= (j+1) * cellSize) {
                        return new Pair( j+ 1, i+ 1);
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

    @FXML
    protected void loadFXML(String fxml, int width, int height, ActionEvent event) {
        try {
            Stage primaryStage = (Stage) this.quit.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuControllerFXML.fxml"));
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root, 650, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
