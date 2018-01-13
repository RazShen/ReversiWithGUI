package ReversiGUI;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button Start_Game;
    @FXML
    private Button Settings;
    @FXML
    private Button Exit;

    @FXML
    protected void startGame() {
        try {
            Stage stage = (Stage) Start_Game.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            HBox root = (HBox) loader.load();
            Scene reversiGameScene = new Scene(root, 800, 605);
            stage.setScene(reversiGameScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         @FXML
        protected void settings() {
            try {
                Stage stage = (Stage) Settings.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));

                VBox root = (VBox) loader.load();
                loader.setController(new SettingsController());
                Scene settingsScene = new Scene(root, 650, 600);

                stage.setScene(settingsScene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    @FXML
    protected void exitGame() {
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

}